import java.io.*;
import java.util.*;
public class buses {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 1; tst <= t; tst++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            long d = Long.parseLong(st.nextToken());
            long[] a = new long[n];
            st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                a[i] = Long.parseLong(st.nextToken());
            }
            long ans = a[n - 1] * ((d)/a[n - 1]);
            for(int i = n - 2; i >= 0; i--){
                ans = a[i] * (ans/a[i]);
            }
            out.println("case #" + tst + ": " + ans);
        }
        out.close();
        in.close();
    }
}
