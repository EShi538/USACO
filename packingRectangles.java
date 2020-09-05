import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class packingRectangles {
    static int w, h, n;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        in.close();
        out.println(solve());
        out.close();
    }
    static long solve(){
        long ans = Long.MAX_VALUE, l = 0, r = (long)1e15;
        while(l <= r){
            long m = (l + r)/2;
            if(valid(m)){
                ans = Math.min(ans, m);
                r = m - 1;
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static boolean valid(long m){
        return BigInteger.valueOf((m / w)).multiply(BigInteger.valueOf(m/h)).compareTo(BigInteger.valueOf(n)) >= 0;
    }
}
