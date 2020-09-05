import java.io.*;
import java.util.*;
public class easyTask {
    static int n, x, y;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = Integer.MAX_VALUE, l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            int m = (l + r)/2;
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
    static boolean valid(int m){
        int firstColumn = m/x;
        int secondColumn = (m - x)/y;
        if(firstColumn + secondColumn >= n){
            return true;
        }
        firstColumn = m/y;
        secondColumn = (m - y)/x;
        return firstColumn + secondColumn >= n;
    }
}
