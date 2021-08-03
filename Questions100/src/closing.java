import java.io.*;
import java.util.*;
public class closing {
    static int n, m;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static int[] order;
    static boolean[] visited;
    static HashSet<Integer> removed = new HashSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("closing.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b); adjList.get(b).add(a);
        }
        order = new int[n];
        for(int i = 0; i < n; i++){
            order[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        visited = new boolean[n + 1];
        int cnt = 0;
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                cnt++;
                dfs(i);
            }
        }
        FileWriter out = new FileWriter("closing.out");
        if(cnt == 1){
            out.write("YES" + "\n");
        }
        else{
            out.write("NO" + "\n");
        }
        for(int i = 0; i < n - 1; i++){
            removed.add(order[i]);
            visited = new boolean[n + 1];
            removeNode(order[i]);
            cnt = 0;
            for(int j = 1; j <= n; j++){
                if(!visited[j] && !removed.contains(j)){
                    cnt++;
                    dfs(j);
                }
            }
            if(cnt == 1){
                out.write("YES" + "\n");
            }
            else{
                out.write("NO" + "\n");
            }
        }
        out.close();
    }
    static void dfs(int node){
        visited[node] = true;
        for(int i = 0; i < adjList.get(node).size(); i++){
            int nextNode = adjList.get(node).get(i);
            if(!visited[nextNode]){
                dfs(nextNode);
            }
        }
    }
    static void removeNode(int node){
        for(int i = 0; i < adjList.get(node).size(); i++){
            int child = adjList.get(node).get(i);
            adjList.get(child).remove((Integer)node);
        }
    }
}
