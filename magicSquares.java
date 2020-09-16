import java.io.*;
import java.util.*;
public class magicSquares {
    static List<Integer> arr = new ArrayList<>();
    static Set<List<Integer>> visited = new HashSet<>();
    static int n;
    static String str;
    static void solve(List<Integer> st){
        Queue<pair> q = new LinkedList<>();
        Queue<String> q1 = new LinkedList<>();
        q1.add("");
        q.add(new pair(st, 0));
        visited.add(st);
        while(!q.isEmpty()){
            pair a = q.poll();
            String curr = q1.poll();
            if(a.a.equals(arr)){
                n = a.d;
                str = curr;
                return;
            }
            List<Integer> reversed = A(new ArrayList<>(a.a));
            List<Integer> changedB = B(new ArrayList<>(a.a));
            List<Integer> changedC = C(new ArrayList<>(a.a));
            if(!visited.contains(reversed)) {
                q.add(new pair(reversed, a.d + 1));
                q1.add(curr + "A");
                visited.add(reversed);
            }
            if(!visited.contains(changedB)) {
                q.add(new pair(changedB, a.d + 1));
                q1.add(curr + "B");
                visited.add(changedB);
            }
            if(!visited.contains(changedC)) {
                q.add(new pair(changedC, a.d + 1));
                q1.add(curr + "C");
                visited.add(changedC);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < 8; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }
        in.close();
        List<Integer> l = new ArrayList<>();
        for(int i = 1; i <= 8; i++){
            l.add(i);
        }
        solve(l);
        out.println(n);
        out.println(str);
        out.close();
    }
    static List<Integer> A(List<Integer> a){
        Collections.reverse(a);
        return a;
    }
    static List<Integer> B(List<Integer> a){
        List<Integer> ret = new ArrayList<>(a);
        ret.set(1, a.get(0));
        ret.set(2, a.get(1));
        ret.set(3, a.get(2));
        ret.set(0, a.get(3));
        ret.set(7, a.get(4));
        ret.set(4, a.get(5));
        ret.set(5, a.get(6));
        ret.set(6, a.get(7));
        return ret;
    }
    static List<Integer> C(List<Integer> a){
        List<Integer> ret = new ArrayList<>(a);
        ret.set(2, a.get(1));
        ret.set(5, a.get(2));
        ret.set(6, a.get(5));
        ret.set(1, a.get(6));
        return ret;
    }
}
class pair{
    List<Integer> a;
    int d;
    public pair(List<Integer> a, int d){
        this.a = a;
        this.d = d;
    }
}