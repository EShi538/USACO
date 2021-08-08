import java.io.*;
import java.util.*;
public class lightson {
    static int n, m;
    static cell[][] arr;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new cell[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = new cell(new ArrayList<>(), false);
            }
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[y - 1][x - 1].switches.add(new coordinate3(a - 1, b - 1));
        }
        in.close();
        visited = new boolean[n][n];
        arr[0][0].isOn = true;
        dfs(0, 0);
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j].isOn){
                    ans++;
                }
            }
        }
        FileWriter out = new FileWriter("lightson.out");
        out.write(Integer.toString(ans));
        out.close();
    }
    static void dfs(int x, int y){
        visited[y][x] = true;
        if(arr[y][x].switches.size() > 0){
            for(coordinate3 i: arr[y][x].switches){
                arr[i.y][i.x].isOn = true;
                if(!visited[i.y][i.x] && isAccessible(i.x, i.y)){
                    dfs(i.x, i.y);
                }
            }
        }
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(inBound(nx, ny) && !visited[ny][nx] && arr[ny][nx].isOn){
                dfs(nx, ny);
            }
        }
    }
    static boolean inBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    static boolean isAccessible(int x, int y){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(inBound(nx, ny) && visited[ny][nx]){
                return true;
            }
        }
        return false;
    }
}
class cell{
    List<coordinate3> switches = new ArrayList<>();
    boolean isOn;
    public cell(List<coordinate3> switches, boolean isOn){
        this.switches = switches;
        this.isOn = isOn;
    }
}
class coordinate3{
    int x, y;
    public coordinate3(int x, int y){
        this.x = x;
        this.y = y;
    }
}