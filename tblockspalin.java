import java.io.*;
import java.util.*;
public class tblockspalin {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 0; tst < t; tst++){
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            Map<Integer, List<Integer>> m = new HashMap<>();
            Map<Integer, int[]> pm = new HashMap<>();
            Set<Integer> s = new HashSet<>();
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
                s.add(a[i]);
            }
            for(int i: s){
                int[] ps = new int[n];
                for(int j = 0; j < n; j++){
                    ps[j] = (j == 0) ? ((a[j] == i) ? 1 : 0) : ((a[j] == i) ? ps[j - 1] + 1 : ps[j - 1]);
                }
                pm.put(i, ps);
            }
            for(int i: s){
                List<Integer> l = new ArrayList<>();
                for(int j = 0; j < n; j++){
                    if(a[j] == i){
                        l.add(j);
                    }
                }
                m.put(i, l);
            }
            int ans = 1;
            for(int i: s){
                for(int j = 1; j <= m.get(i).size()/2; j++){
                    int max = -1;
                    int l = m.get(i).get(j - 1);
                    int r = m.get(i).get(m.get(i).size() - j);
                    for(int k: s){
                        max = Math.max(max, pm.get(k)[r - 1] - pm.get(k)[l]);
                    }
                    ans = Math.max(ans, 2 * j + max);
                }
            }
            out.println(ans);
        }
        out.close();
        in.close();
    }
}