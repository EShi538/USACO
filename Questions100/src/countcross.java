import java.io.*;
import java.util.*;
public class countcross {
    static int n, k, r;
    static cell1[][] farm;
    static List<Integer> groupSizes = new ArrayList<>();
    static boolean[][] visited;
    static int cowCount = 0;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("countcross.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        farm = new cell1[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                farm[i][j] = new cell1(new HashSet<>(), false);
            }
        }
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken()), x1 = Integer.parseInt(st.nextToken());
            farm[y - 1][x - 1].roads.add(new pair(x1 - 1, y1 - 1));
            farm[y1 - 1][x1 - 1].roads.add(new pair(x - 1, y - 1));
        }
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
            farm[y - 1][x - 1].hasCow = true;
        }
        in.close();
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    cowCount = 0;
                    floodfill(j, i);
                    groupSizes.add(cowCount);
                }
            }
        }
        int ans = 0;
        for(int i: groupSizes){
            ans += (i * (i - 1))/2;
        }
        FileWriter out = new FileWriter("countcross.out");
        out.write(Integer.toString((k * (k - 1))/2 - ans));
        out.close();
    }
    static void floodfill(int x, int y){
        visited[y][x] = true;
        if(farm[y][x].hasCow){
            cowCount++;
        }
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(inBound(nx, ny) && !visited[ny][nx] && !farm[y][x].roads.contains(new pair(nx, ny))){
                floodfill(nx, ny);
            }
        }
    }
    static boolean inBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
class cell1{
    HashSet<pair> roads;
    boolean hasCow;
    public cell1(HashSet<pair> roads, boolean hasCow){
        this.roads = roads;
        this.hasCow = hasCow;
    }
}
class pair{
    int x, y;
    public pair(int x, int y){
        this.x = x;
        this.y =y;
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof pair)){
            return false;
        }
        pair p = (pair) o;
        return this.x == p.x && this.y == p.y;
    }
    @Override
    public int hashCode(){
        return (10 * x) + y;
    }
}