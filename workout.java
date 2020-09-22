import java.io.*;
import java.util.*;
public class workout {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 1; tst <= t; tst++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] a = new int[n];
            st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            out.println("case #" + tst + ": " + solve(a, k));
        }
        out.close();
        in.close();
    }
    static int solve(int[] a, int k){
        int ans = Integer.MAX_VALUE, l = 1, r = Integer.MAX_VALUE - 1;
        while(l <= r){
            int m = (l + r)/2;
            if(valid(m, a, k)){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static boolean valid(int m, int[] a, int k) {
        int cnt = 0;
        if(m == 50){
            int x = 0;
        }
        for(int i = 1; i < a.length; i++){
            int d = a[i] - a[i - 1];
            int div = d/m;
            if(d % m == 0){
                div--;
            }
            cnt += div;
        }
        return cnt <= k;
    }
}