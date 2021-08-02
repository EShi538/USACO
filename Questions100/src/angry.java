import java.io.*;
import java.util.*;
public class angry {
    static int n, k;
    static long[] x;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("angry.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = new long[n];
        for(int i = 0; i < n; i++){
            x[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        long l = 1, r = Integer.MAX_VALUE;
        Arrays.sort(x);
        long ans = Integer.MAX_VALUE;
        while(l <= r){
            long m = (l + r)/2;
            if(valid(m)){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        FileWriter out = new FileWriter(new File("angry.out"));
        out.write(Long.toString(ans));
        out.close();
    }
    static boolean valid(long R){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            int start = i;
            cnt++;
            while(i < n && x[i] < x[start] + (2 * R)){
                i++;
            }
            if(i < n && x[i] != x[start] + (2 * R)){
                i--;
            }
        }
        return cnt <= k;
    }
}
