import java.io.*;
import java.util.*;
public class cowsStalls {
    static int n, k;
    static int[] s;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        s = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            s[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = -1, l = 1, r = Integer.MAX_VALUE - 1;
        while(l <= r){
            int m = (l + r)/2;
            if(valid(m)){
                ans = Math.max(ans, m);
                l = m + 1;
            }
            else{
                r = m - 1;
            }
        }
        return ans;
    }
    static boolean valid(int m){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            int pos = Arrays.binarySearch(s, s[i] + m);
            if(pos < 0){
                pos = -pos - 1;
            }
            cnt++;
            i = pos - 1;
        }
        return cnt >= k;
    }
}
