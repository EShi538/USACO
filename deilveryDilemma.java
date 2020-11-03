import java.io.*;
import java.util.*;
public class deilveryDilemma {
    static int t;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = Integer.parseInt(in.readLine());
        for(int ts = 0; ts < t; ts++){
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n], b = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                b[i] = Integer.parseInt(st.nextToken());
            }
            out.println(solve(n, a, b));
        }
        out.close();
        in.close();
    }
    static int solve(int n, int[] a, int [] b){
        int ans = Integer.MAX_VALUE, l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            int m = (l + r)/2;
            if(valid(m, n, a, b)){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static boolean valid(int m, int n, int[] a, int[] b){
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(a[i] <= m){
                visited[i] = true;
            }
        }
        long sum = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                sum += b[i];
            }
        }
        return sum <= m;
    }
}
