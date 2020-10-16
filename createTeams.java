import java.io.*;
import java.util.*;
public class createTeams {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 0; tst < t; tst++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a);
            for(int i = 0; i < n/2; i++){
                int tmp = a[i];
                a[i] = a[n - i - 1];
                a[n - i - 1] = tmp;
            }
            int s = 0;
            int ans = 0;
            for(int i = 0; i < n; i++){
                s++;
                int d = (x % a[i] == 0) ? x/a[i] : x/a[i] + 1;
                if(d <= s){
                    ans++;
                    s = 0;
                }
            }
            out.println(ans);
        }
        out.close();
        in.close();
    }
}
