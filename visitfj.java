import java.io.*;
import java.util.*;
public class visitfj {
    static int n, t;
    static int[][] f;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("visitfj.in"));
        PrintWriter out = new PrintWriter(new File("visitfj.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        f = new int[n][n];
        visited = new boolean[n][n][3];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                f[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        PriorityQueue<p> q = new PriorityQueue<>(new sort());
        q.add(new p(0, 0, 0, 0));
        visited[0][0][0] = true;
        while(!q.isEmpty()){
            p curr = q.poll();
            if(curr.x == n - 1 && curr.y == n - 1){
                return curr.t;
            }
            if((curr.l + 1) % 3 == 0){
                for(int i = 0; i < 4; i++){
                    int nx = curr.x + dx[i], ny = curr.y + dy[i];
                    if(inBound(nx, ny) && !visited[nx][ny][(curr.l + 1) % 3]){
                        q.add(new p(nx, ny, curr.l + 1, curr.t + t + f[ny][nx]));
                        visited[nx][ny][(curr.l + 1) % 3] = true;
                    }
                }
            }
            else{
                for(int i = 0; i < 4; i++){
                    int nx = curr.x + dx[i], ny = curr.y + dy[i];
                    if(inBound(nx, ny) && !visited[nx][ny][(curr.l + 1) % 3]){
                        q.add(new p(nx, ny, curr.l + 1, curr.t + t));
                        visited[nx][ny][(curr.l + 1) % 3] = true;
                    }
                }
            }
        }
        return -1;
    }
    static boolean inBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
class p{
    int x, y, l, t;
    public p(int x, int y, int l, int t){
        this.x = x;
        this.y = y;
        this.l = l;
        this.t = t;
    }
}
class sort implements Comparator<p>{
    public int compare(p a, p b){
        return a.t - b.t;
    }
}
class state{
    int x, y, t;
    public state(int x, int y, int t){
        this.x = x;
        this.y = y;
        this.t = t;
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof state)){
            return false;
        }
        state s = (state) o;
        return this.x == s.x && this.y == s.y && this.t == s.t;
    }
}