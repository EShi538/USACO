import java.io.*;
import java.util.*;
public class C {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 0; tst < t; tst++){
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            TreeSet<Integer> s = new TreeSet<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            Map<Integer, List<Integer>> m = new HashMap<>();
            for(int i = 0; i < n; i++){
                int x = Integer.parseInt(st.nextToken());
                a[i] = x; s.add(x);
            }
            for(int i = 0; i < n; i++){
                List<Integer> s1 = (m.containsKey(a[i])) ? m.get(a[i]) : new ArrayList<>();
                s1.add(i);
                m.put(a[i], s1);
            }
            int[] ans = new int[n];
            for(int i = 0; i < n; i++){
                ans[i] = -1;
            }
            for(int i: s){
                int g = n - m.get(i).get(m.get(i).size() - 1) - 1;
                for(int j = 0; j < m.get(i).size(); j++){
                    if(j == 0){
                        g = Math.max(g, m.get(i).get(0));
                    }
                    else {
                        g = Math.max(g, m.get(i).get(j) - m.get(i).get(j - 1) - 1);
                    }
                }
                for(int j = g; j < n; j++){
                    if(ans[j] != -1){
                        break;
                    }
                    ans[j] = i;
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
