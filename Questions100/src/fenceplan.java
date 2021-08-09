import java.io.*;
import java.util.*;
public class fenceplan {
    static int n, m;
    static List<coordinate4> cows = new ArrayList<>();
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static boolean[] visited;
    static int maxX, maxY, minX, minY;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("fenceplan.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        cows.add(new coordinate4(-1, -1));
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            cows.add(new coordinate4(x, y));
        }
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b); adjList.get(b).add(a);
        }
        in.close();
        int ans = Integer.MAX_VALUE;
        visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++){
            maxX = -1; maxY = -1;
            minX = Integer.MAX_VALUE; minY = Integer.MAX_VALUE;
            if(!visited[i]){
                dfs(i);
                int perimeter = (2 * (maxX - minX))  + (2 * (maxY - minY));
                ans = Math.min(ans, perimeter);
            }
        }
        FileWriter out = new FileWriter("fenceplan.out");
        out.write(Integer.toString(ans));
        out.close();
    }
    static void dfs(int node){
        visited[node] = true;
        maxX = Math.max(cows.get(node).x, maxX);
        maxY = Math.max(cows.get(node).y, maxY);
        minX = Math.min(cows.get(node).x, minX);
        minY = Math.min(cows.get(node).y, minY);
        for(int i: adjList.get(node)){
            if(!visited[i]){
                dfs(i);
            }
        }
    }
}
class coordinate4{
    int x, y;
    public coordinate4(int x, int y){
        this.x = x;
        this.y = y;
    }
}