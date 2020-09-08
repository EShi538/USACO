import java.io.*;
import java.util.*;
public class factoryMachines {
    static int n, t;
    static int[] k;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        k = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            k[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static long solve(){
        long ans = Long.MAX_VALUE, l = 0, r = (long)1e18 + 1;
        while(l <= r){
            long m = (l + r)/2;
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
    static boolean valid(long m){
        long productCnt = 0;
        for(int i = 0; i < n; i++){
            productCnt += m/(long)k[i];
            if(productCnt >= t){
                return true;
            }
        }
        return productCnt >= t;
    }
}
