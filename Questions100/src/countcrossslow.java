import java.io.*;
import java.util.*;
public class countcrossslow {
    static int n = 0;
    static int k = 0;
    static int r = 0;
    static Set<road> roads = new HashSet<road>();
    static List<coord> cows = new ArrayList<coord>();
    static boolean[][] visited = new boolean[n][n];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean done = false;
    public static void DFS(coord curr, coord end){
        if(done == true){
            return;
        }
        visited[curr.y][curr.x] = true;
        if(curr.x == end.x && curr.y == end.y){
            done = true;
            return;
        }
        for(int i = 0; i < 4; i++){
            int nx = curr.x + dx[i];
            int ny = curr.y + dy[i];
            if(inBound(nx, ny) && visited[ny][nx] == false && noRoad(curr.x, curr.y, nx, ny)){
                DFS(new coord(nx, ny), end);
                if(done == true){
                    return;
                }
            }
        }
        return;
    }

    public static boolean inBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    public static boolean noRoad(int x, int y, int nx, int ny){
        if(roads.contains(new road(x, y, nx, ny))){
            return false;
        }
        return true;      
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("countcross.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        visited = new boolean[n][n];
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            roads.add(new road(r, c, r1, c1));
            roads.add(new road(r1, c1, r, c));
        }
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(in.readLine());
            cows.add(new coord(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }
        in.close();

        int cnt = 0;
        int tot = 0;
        for(int i = 0; i < k; i++){
            for(int j = i + 1; j < k; j++){
                tot++;
                coord start = cows.get(i);
                coord dest = cows.get(j);
                DFS(start, dest);
                if(done == true){
                    cnt++;
                    done = false;
                }
                visited = new boolean[n][n];
            }
        }
        File out = new File("countcross.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(tot - cnt));
        writer.close();
    }
}

class road{
    int r1;
    int c1;
    int r2;
    int c2;
    public road(int r1, int c1, int r2, int c2){
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof road)){
            return false;
        }
        road p = (road) o;
        return this.r1 == p.r1 && this.c1 == p.c1 && this.r2 == p.r2 && this.c2 == p.c2;
    }
    @Override
    public int hashCode(){
        return 1000 * r1 + 100 * c1 + 10 * r2 + c2;
    }
}

class coord{
    int x;
    int y;
    public coord(int x, int y){
        this.x = x;
        this.y = y;
    }
}