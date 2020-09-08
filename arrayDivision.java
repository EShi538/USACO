import java.io.*;
import java.util.*;
public class arrayDivision {
    static int n, k;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static long solve(){
        long ans = Long.MAX_VALUE, l = 0, r = (long)1e16;
        while(l <= r){
            long m = (l + r)/2;
            if(r == 19){
                int x= 0;
            }
            if(valid(m)){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static boolean valid(long m){
        if(m == 5){
            int x = 0;
        }
        int p = 0;
        int subArrays = 0;
        for(int i = 0; i < n; i++){
            long sum = 0;
            boolean looped = false;
            while(p < n && sum + a[p] <= m){
                looped = true;
                sum += a[p];
                p++;
            }
            if(!looped){
                return false;
            }
            i = p - 1;
            subArrays++;
        }
        return subArrays <= k;
    }
}
