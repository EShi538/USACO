import java.io.*;
import java.util.*;
public class tractor {
    static int n, stx, sty;
    static boolean[][] mat = new boolean[1002][1002];
    static boolean[][] visited = new boolean[1002][1002];
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean good = false;
    static boolean inBound(int x, int y){
        return x >= 0 && x <= 1001 && y >= 0 && y <= 1001;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        stx = Integer.parseInt(st.nextToken());
        sty = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            mat[y][x] = true;
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = Integer.MAX_VALUE, l = 0, r = 50001;
        while(l <= r){
            int m = (l + r)/2;
            visited = new boolean[1002][1002];
            good = false;
            dfs(m);
            if(good){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static void dfs(int m){
        Stack<s> st = new Stack<>();
        st.add(new s(stx, sty, 0));
        while(!st.isEmpty()) {
            s curr = st.pop();
            if (curr.x == 0 || curr.y == 0) {
                good = true;
                return;
            }
            visited[curr.y][curr.x] = true;
            boolean added = false;
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i], ny = curr.y + dy[i];
                if (inBound(nx, ny) && !visited[ny][nx]) {
                    if (mat[ny][nx]) {
                        if (curr.tc < m) {
                            added = true;
                            st.add(new s(nx, ny, curr.tc + 1));
                        }
                    }
                    else {
                        added = true;
                        st.add(new s(nx, ny, curr.tc));
                    }
                }
            }
            if(!added) {
                visited[curr.y][curr.x] = false;
            }
        }
    }
}
class s{
    int x, y, tc;
    public s(int x, int y, int tc){
        this.x = x;
        this.y = y;
        this.tc = tc;
    }
}