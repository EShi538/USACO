import java.io.*;
import java.util.*;
public class B {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 0; tst < t; tst++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
            int[] a = new int[n];
            st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                int x = Integer.parseInt(st.nextToken());
                a[i] = x;
            }
            int[] ans = new int[n];
            boolean[] processed = new boolean[n];
            Map<Integer, Set<Integer>> m = new HashMap<>();
            for(int i = 0; i < n; i++){
                Set<Integer> s = (m.containsKey(a[i])) ? m.get(a[i]) : new HashSet<>();
                s.add(i);
                m.put(a[i], s);
            }
            for(int i = 0; i < n; i++){
                if (processed[i]) {
                    continue;
                }
                if(2 * a[i] == k){
                    boolean b = false;
                    for(int j: m.get(a[i])){
                        ans[j] = (b) ? 1 : 0;
                        processed[j] = true;
                        b =! b;
                    }
                }
                else {
                    for (int j : m.get(a[i])) {
                        ans[j] = 0;
                        processed[j] = true;
                    }
                    if (m.containsKey(k - a[i])) {
                        for (int j : m.get(k - a[i])) {
                            ans[j] = 1;
                            processed[j] = true;
                        }
                    }
                }
            }
            for(int i: ans){
                out.print(i + " ");
            }
            out.println();
        }
        out.close();
        in.close();
    }
}