import java.io.*;
import java.util.*;
public class wormsort {
    static int n, m;
    static int[] p;
    static List<ArrayList<connection1>> adjList = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("wormsort.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            p[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<connection1>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new connection1(b, w)); adjList.get(b).add(new connection1(a, w));
        }
        in.close();
        long l = 0, r = Integer.MAX_VALUE;
        long ans = -1;
        while(l <= r){
            long m = (l + r)/2;
            visited = new boolean[n + 1];
            dfs(1, m);
            if(valid()){
                l = m + 1;
                ans = Math.max(ans, m);
            }
            else{
                r = m - 1;
            }
        }
        FileWriter out = new FileWriter("wormsort.out");
        out.write(Long.toString(ans));
        out.close();
    }
    static void dfs(int node, long m){
        visited[node] = true;
        for(connection1 i: adjList.get(node)){
            if(!visited[i.node] && i.connection >= m){
                dfs(i.node, m);
            }
        }
    }
    static boolean valid(){
        for(boolean i: visited){
            if(!i){
                return false;
            }
        }
        return true;
    }
}
class connection1{
    int node, connection;
    public connection1(int node, int connection){
        this.node = node;
        this.connection = connection;
    }
}