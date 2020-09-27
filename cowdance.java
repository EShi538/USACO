import java.io.*;
import java.util.*;
public class cowdance {
    static int n, t;
    static int[] c;
    public static void main(String[] args) throws Exception{
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter(new File("cowdance.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        c = new int[n];
        for(int i = 0; i < n; i++){
            c[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = Integer.MAX_VALUE, l = 1, r = n + 1;
        while(l <= r){
            int m = (l + r)/2;
            if(valid(m)){
                r = m - 1;
                ans = Math.min(m, ans);
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static boolean valid(int m){
        int tt = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(new sort());
        for(int i = 0; i < m; i++){
            q.add(c[i] + tt);
        }
        for(int i = m; i < n; i++){
            tt = q.poll();
            q.add(c[i] + tt);
            if(tt > t){
                return false;
            }
        }
        while(!q.isEmpty()){
            tt = q.poll();
        }
        return tt <= t;
    }
}
class sort implements Comparator<Integer>{
    public int compare(Integer a, Integer b){
        return a - b;
    }
}