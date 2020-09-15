import java.io.*;
import java.util.*;
public class sleepy {
    static int n;
    static int[] a;
    static int[] BIT = new int[100100];
    static void inc(int i) {
        for(i++; i < 100100; i += (i & -i))
            BIT[i]++;
    }
    static int getSum(int i) {
        int c = 0;
        for(i++; i > 0;i -=( i & -i))
            c += BIT[i];
        return c;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter out = new PrintWriter(new File("sleepy.out"));
        n = Integer.parseInt(in.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        int p = n - 2;
        while(p >= 0 && a[p] < a[p + 1]){
            p--;
        }
        p++;
        int ans = 0;
        Queue<Integer> todo = new LinkedList<>();
        for(int i = 0; i < p; i++){
            todo.add(a[i]);
        }
        int s = todo.size();
        ans += s;
        out.println(ans);
        int[] moves = new int[ans];
        for(int i = ans; i < n; i++){
            inc(a[i]);
        }
        for(int i = 0; i < s; i++){
            int curr = todo.poll();
            moves[i] = (ans - i - 1) + getSum(curr);
            inc(curr);
        }
        for(int i = 0; i < moves.length; i++){
            if(i < moves.length - 1) {
                out.print(moves[i] + " ");
            }
            else{
                out.print(moves[i]);
            }
        }
        out.close();
    }
}
