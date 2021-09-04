import java.io.*;
import java.util.*;
public class cowntagion {
    static int n;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static boolean[] visited;
    static int days = 0;
    static void dfs(int node){
        visited[node] = true;
        if(node == 1){
            days += log2(adjList.get(node).size()) + 1;
        }
        else{
            if(!(adjList.get(node).size() == 1)){
                days += log2(adjList.get(node).size() - 1) + 1;
            }
        }
        for(int i: adjList.get(node)){
            if(!visited[i]){
                days++;
                dfs(i);
            }
        }
    }
    static int log2(int x){
        return (int)Math.floor((Math.log(x) / Math.log(2)));
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n - 1; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        in.close();
        visited = new boolean[n + 1];
        dfs(1);
        PrintWriter out = new PrintWriter(System.out);
        out.write(Integer.toString(days));
        out.close();
    }
}