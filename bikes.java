import java.io.*;
import java.util.*;
public class bikes {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 1; tst <= t; tst++){
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            int ans = 0;
            for(int i = 1; i < n - 1; i++){
                if(a[i] > a[i - 1] && a[i] > a[i + 1]){
                    ans++;
                }
            }
            out.println("case #" + tst + ": " + ans);
        }
        out.close();
    }
}
