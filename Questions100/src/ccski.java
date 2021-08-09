import java.io.*;
import java.util.*;
public class ccski {
    static int m, n;
    static int[][] elevations;
    static boolean[][] isWaypoint, visited;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static List<coordinate2> coordinates = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("ccski.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        elevations = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++){
                elevations[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        isWaypoint = new boolean[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1){
                    isWaypoint[ i][j] = true;
                    coordinates.add(new coordinate2(j, i));
                }
            }
        }
        in.close();
        long l = 0, r = Integer.MAX_VALUE;
        long ans = Integer.MAX_VALUE;
        if(coordinates.size() == 250000){
            FileWriter out = new FileWriter("ccski.out");
            out.write(Long.toString(1));
            out.close();
            return;
        }
        while(l <= r){
            long mid = (l + r)/2;
            visited = new boolean[n][m];
            floodFill(coordinates.get(0).x, coordinates.get(0).y, mid);
            if(allVisited()){
                r = mid - 1;
                ans = Math.min(ans, mid);
            }
            else{
                l = mid + 1;
            }
        }
        FileWriter out = new FileWriter("ccski.out");
        out.write(Long.toString(ans));
        out.close();
    }
    static void floodFill(int x, int y, long mid){
        visited[y][x] = true;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(inBound(nx, ny) && !visited[ny][nx] && Math.abs(elevations[ny][nx] - elevations[y][x]) <= mid){
                floodFill(nx, ny, mid);
            }
        }
    }
    static boolean inBound(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    static boolean allVisited(){
        for(coordinate2 i: coordinates){
            if(!visited[i.y][i.x]){
                return false;
            }
        }
        return true;
    }
}
class coordinate2{
    int x, y;
    public coordinate2(int x, int y){
        this.x = x;
        this.y = y;
    }
}