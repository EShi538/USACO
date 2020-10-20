import java.io.*;
import java.util.*;
public class maxCoins {
    static long check(int sx, int sy, int[][] a, int n){
        long sum = 0;
        while(inBound(sx, sy, n)){
            sum += a[sy][sx];
            sy++; sx++;
        }
        return sum;
    }
    static boolean inBound(int x, int y, int n){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 1; tst <= t; tst++){
            int n = Integer.parseInt(in.readLine());
            int[][] a = new int[n][n];
            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(in.readLine());
                for(int j = 0; j < n; j++){
                    a[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            long ans = -1;
            for(int i = 0; i < n; i++){
                ans = Math.max(ans, check(i, 0, a, n));
            }
            for(int i = 0; i < n; i++){
                ans = Math.max(ans, check(0, i, a, n));
            }
            out.println("Case #" + tst + ": " + ans);
        }
        out.close();
        in.close();
    }
}
