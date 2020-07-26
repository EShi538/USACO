import java.io.*;
import java.util.*;
public class bearFriendship {
    static boolean[] visited;
    static long nodeCnt = 0;
    static long edgeCnt = 0;
    static void dfs(int node, List<ArrayList<Integer>>adjList){
        nodeCnt++;
        visited[node] = true;
        List<Integer> adj = adjList.get(node);
        for(int i: adj){
            edgeCnt++;
            if(!visited[i]){
                dfs(i, adjList);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        List<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        in.close();

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                dfs(i, adjList);
                if(edgeCnt != ((nodeCnt) * (nodeCnt - 1))){
                    System.out.println("NO");
                    return;
                }
                edgeCnt = 0; nodeCnt = 0;
            }
        }
        System.out.println("YES");
    }
}
