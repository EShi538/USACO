import java.io.*;
import java.util.*;
public class minHeightTree {
    static int t;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = Integer.parseInt(in.readLine());
        for(int ts = 1; ts <= t; ts++){
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            List<Integer> sizes = new ArrayList<>();
            for(int i = 1; i < n; i++){
                int p = i, cnt = 1;
                if(i == n - 1){
                    sizes.add(1);
                }
                else{
                    while(p < n - 1 && a[p] < a[p + 1]){
                        cnt++;
                        p++;
                    }
                    sizes.add(cnt);
                    i = p;
                }
            }
            int sum = sizes.get(0), ans = 1;
            for(int i = 1; i < sizes.size(); i++){
                int nSum = 0, j;
                ans++;
                for(j = i; j < Math.min(i + sum, sizes.size()); j++){
                    nSum += sizes.get(j);
                }
                sum = nSum;
                i = j - 1;
            }
            out.println(ans);
        }
        in.close();
        out.close();
    }
}
