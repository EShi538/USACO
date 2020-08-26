import java.io.*;
import java.util.*;
public class loan {
    static long n, k, m;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("loan.in"));
        PrintWriter out = new PrintWriter(new File("loan.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        in.close();
        out.println(solve());
        out.close();
    }
    static long solve(){
        long ans = -1, l = 0, r = (long)10e12;
        while(l <= r){
            long x = (l + r)/2;
            if(valid(x)){
                ans = Math.max(ans, x);
                l = x + 1;
            }
            else{
                r = x - 1;
            }
        }
        return ans;
    }
    static boolean valid(long x){
        long g = 0;
        long days = 0;
        while(true){
            if(days > k){
                return false;
            }
            if(g >= n){
                return true;
            }
            long remaining = n - g, y = remaining/x, closestMultX = remaining - (remaining % x), gap = remaining - closestMultX;
            if(y <= m){
                return g + (m * (k - days)) >= n;
            }
            long canFullyFit = (gap == 0) ? 1 : gap/y,  excess = gap % y;
            days += canFullyFit;
            g += y * canFullyFit;
            if(excess != 0){
                days++;
                g += y;
            }
        }
    }
}
