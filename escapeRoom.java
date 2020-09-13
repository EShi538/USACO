import java.io.*;
import java.util.*;
public class escapeRoom {
    static int m, n;
    static int[][] a;
    static Map<Integer, List<point>> map = new HashMap<>();
    static boolean[][] visited;
    static boolean found = false;
    static void dfs(int x, int y){
        if(x == n - 1 && y == m - 1){
            found = true;
            return;
        }
        visited[y][x] = true;
        List<point> adj = (map.containsKey(a[y][x])) ? map.get(a[y][x]) : new ArrayList<>();
        for(point i: adj){
            if(!visited[i.y][i.x]){
                dfs(i.x, i.y);
                if(found){
                    return;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        m = Integer.parseInt(in.readLine());
        n = Integer.parseInt(in.readLine());
        a = new int[m][n];
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        in.close();
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                int prod = i * j;
                String pos = i + Integer.toString(j);
                List<point> l = (map.containsKey(prod)) ? map.get(prod) : new ArrayList<>();
                l.add(new point(j - 1, i - 1));
                map.put(prod, l);
            }
        }
        visited = new boolean[m][n];
        dfs(0, 0);
        if(found){
            out.println("yes");
        }
        else{
            out.println("no");
        }
        out.close();
    }
}
class point{
    int x, y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
