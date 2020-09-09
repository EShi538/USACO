import java.io.*;
import java.util.*;
public class hayfeast {
    static long n, m;
    static List<haybale> haybales = new ArrayList<>();
    static int[] fs;
    static int[][] lookup;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("hayfeast.in"));
        PrintWriter out = new PrintWriter(new File("hayfeast.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int f = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
            haybales.add(new haybale(f, s));
        }
        in.close();
        fs = new int[(int)n];
        for(int i = 0; i < n; i++){
            fs[i] = haybales.get(i).s;
        }
        lookup = new int[(int)Math.floor(Math.log(n)/Math.log(2)) + 1][(int)n];
        buildSparseTable(fs, (int)n);
        int p = 0;
        long sum = 0;
        long ans = Long.MAX_VALUE;
        for(int i = 0; i < n; i++){
            p = Math.max(i, p);
            if(i != 0) {
                sum -= haybales.get(i - 1).f;
            }
            while(p < n && sum < m) {
                sum += haybales.get(p).f;
                p++;
            }
            if(sum >= m) {
                int len = p - i;
                int p1 = (int) Math.floor(Math.log(len) / Math.log(2));
                int k = (int) Math.pow(2, p1);
                ans = Math.min(ans, Math.max(lookup[p1][i], lookup[p1][p - k]));
            }
        }
        out.println(ans);
        out.close();
    }
    static void buildSparseTable(int[] arr, int n) {
        int h = (int) Math.floor(Math.log(n)/Math.log(2));
        if (n >= 0) System.arraycopy(arr, 0, lookup[0], 0, n);
        for(int i = 1; i <= h; i++){
            for(int j = 0; j + (1 << i) <= n; j++){
                lookup[i][j] = Math.max(lookup[i - 1][j], lookup[i - 1][j + (1 << (i - 1))]);
            }
        }
    }
}
class haybale{
    int f, s;
    public haybale(int f, int s){
        this.f = f;
        this.s = s;
    }
}