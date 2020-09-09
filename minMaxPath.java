import java.io.*;
import java.util.*;
public class minMaxPath {
    static int n, m, d;
    static List<ArrayList<edge>> adjList = new ArrayList<>();
    static LinkedHashSet<Integer> path;
    static LinkedHashSet<Integer> bestPath = new LinkedHashSet<>();
    static int le, bl;
    static boolean[] visited;
    static boolean done = false, good = false;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new edge(b, c));
        }
        in.close();
        solve();
        if(!good){
            out.println(-1);
            out.close();
            return;
        }
        out.println(bl);
        for(int i: bestPath){
            out.print(i + " ");
        }
        out.close();
    }
    static void solve(){
        long l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            long mid = (l + r)/2;
            visited = new boolean[n + 1];
            path = new LinkedHashSet<>();
            path.add(1);
            done = false;
            dfs(1, 0, mid);
            if(done){
                good = true;
                bestPath = new LinkedHashSet<>();
                bestPath.addAll(path);
                bl = le;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
    }
    static void dfs(int node, int length, long mid){
        if(length > d){
            return;
        }
        visited[node] = true;
        if(node == n){
            le = length;
            done = true;
            return;
        }
        List<edge> adj = adjList.get(node);
        for(edge i: adj){
            if(!visited[i.b] && i.c <= mid){
                path.add(i.b);
                dfs(i.b, length + 1, mid);
                if(done){
                    return;
                }
                path.remove(i.b);
                visited[i.b] = false;
            }
        }
    }
}
class edge{
    int b, c;
    public edge(int b, int c){
        this.b = b;
        this.c = c;
    }
}