import java.io.*;
import java.util.*;
public class mouse {
    static int n, m, k;
    static boolean[][] matrix;
    static long[][] dp;
    static final int mod = (int)1e9 + 7;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new boolean[n][m];
        dp = new long[n][m];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1, y = Integer.parseInt(st.nextToken()) - 1;
            matrix[x][y] = true;
        }
        in.close();
        dp[0][0] = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j]){
                    continue;
                }
                if(!(i == 0 || matrix[i - 1][j])){
                    dp[i][j] += dp[i - 1][j] % mod;
                }
                if(!(j == 0 || matrix[i][j - 1])){
                    dp[i][j] += dp[i][j - 1] % mod;
                }
            }
        }
        out.println(dp[n - 1][m - 1] % mod);
        out.close();
    }
}