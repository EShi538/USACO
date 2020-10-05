import java.io.*;
import java.util.*;
public class mootube {
    static int n, q;
    static List<ArrayList<edge>> adjList = new ArrayList<>();
    static boolean[] visited;
    static int cnt = 0;
    static void dfs(int node, int k){
        visited[node] = true;
        cnt++;
        for(edge i: adjList.get(node)){
            if(!visited[i.b] && i.e >= k){
                dfs(i.b, k);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter out = new PrintWriter(new File("mootube.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new edge(b, e));
            adjList.get(b).add(new edge(a, e));
        }
        for(int i = 0; i < q; i++){
            visited = new boolean[n + 1];
            cnt = 0;
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            dfs(v, k);
            out.println(cnt - 1);
        }
        in.close();
        out.close();
    }
}
class edge{
    int b, e;
    public edge(int n, int e){
        this.b = n;
        this.e = e;
    }
}