import java.io.*;
import java.util.*;
public class perfectSubarray {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 1; tst <= t; tst++){
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n], ps = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
                max = Math.max(a[i], max);
            }
            for(int i = 0; i < n; i++){
                ps[i] = (i == 0) ? a[0] : ps[i - 1] + a[i];
            }
            int ans = 0;
            for(int j = 0; j <= Math.sqrt(n * max) + 1; j++) {
                int s = (int)Math.pow(j, 2);
                Map<Integer, Integer> m = new HashMap<>();
                m.put(0, 1);
                for (int i = 0; i < n; i++) {
                    int g = ps[i] - s;
                    if(m.containsKey(g)){
                        ans += m.get(g);
                    }
                    if(m.containsKey(ps[i])){
                        m.put(ps[i], m.get(ps[i]) + 1);
                    }
                    else{
                        m.put(ps[i], 1);
                    }
                }
            }
            out.println("case #" + tst + ": " + ans);
        }
        in.close();
        out.close();
    }
}
