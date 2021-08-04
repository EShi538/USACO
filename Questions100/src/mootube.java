import java.io.*;
import java.util.*;
public class mootube {
    static int n, q;
    static List<ArrayList<connection>> adjList = new ArrayList<>();
    static boolean[] visited;
    static int cnt = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new connection(b, r)); adjList.get(b).add(new connection(a, r));
        }
        FileWriter out = new FileWriter("mootube.out");
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            visited = new boolean[n + 1];
            cnt = 0;
            dfs(v, k);
            out.write(Integer.toString(cnt - 1) + "\n");
        }
        in.close();
        out.close();
    }
    static void dfs(int node, int k){
        visited[node] = true;
        cnt++;
        for(connection i: adjList.get(node)){
            if(!visited[i.node] && i.relevance >= k){
                dfs(i.node, k);
            }
        }
    }
}
class connection{
    int node, relevance;
    public connection(int node, int relevance){
        this.node = node;
        this.relevance = relevance;
    }
}