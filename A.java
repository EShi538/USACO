import java.io.*;
import java.util.*;
public class A {
    static int t;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = Integer.parseInt(in.readLine());
        for(int tst = 0; tst < t; tst++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
            int[] a = new int[n];
            st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a);
            int ans = 0;
            for(int i = 1; i < n; i++){
                ans += (k - a[i])/a[0];
            }
            out.println(ans);
        }
        out.close();
        in.close();
    }
}
