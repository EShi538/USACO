import java.io.*;
import java.util.*;
public class ccski {
    static int n, m;
    static int[][] heights;
    static boolean[][] isWP, visited;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int WPcnt, origWPcnt;
    static boolean done = false;
    static int sx, sy;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("ccski.in"));
        PrintWriter out = new PrintWriter(new FileWriter("ccski.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        heights = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++){
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        isWP = new boolean[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++){
                int num = Integer.parseInt(st.nextToken());
                isWP[i][j] = num == 1;
                if(num == 1){
                    origWPcnt++;
                    sx = j;
                    sy = i;
                }
            }
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = Integer.MAX_VALUE, l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            int mid = (l + r)/2;
            visited = new boolean[n][m];
            done = false;
            WPcnt = 0;
            valid(sx, sy, mid);
            if(done){
                r = mid - 1;
                ans = Math.min(ans, mid);
            }
            else{
                l = mid + 1;
            }
        }
        return ans;
    }
    static void valid(int x, int y, int mid){
        visited[y][x] = true;
        if(isWP[y][x]){
            WPcnt++;
        }
        if(WPcnt >= origWPcnt){
            done = true;
            return;
        }
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(inBounds(nx, ny) && !visited[ny][nx] && Math.abs(heights[y][x] - heights[ny][nx]) <= mid){
                valid(nx, ny, mid);
                if(done){
                    return;
                }
                //visited[ny][nx] = false;
            }
        }
    }
    static boolean inBounds(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
