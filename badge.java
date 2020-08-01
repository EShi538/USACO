import java.io.*;
import java.util.*;
public class badge{
    static List<TreeSet<Integer>> adjList = new ArrayList<>();
    static boolean[] visited;
    static int result = -1;
    static boolean done = false;
    static void dfs(int node){
        visited[node] = true;
        TreeSet<Integer> adj = adjList.get(node);
        for(int i: adj){
            if(!visited[i]){
                dfs(i);
                if(done){
                    return;
                }
            }
            else{
                done = true;
                result = i;
                return;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] report = new int[n];
        for(int i = 0; i < n; i++){
            report[i] = Integer.parseInt(st.nextToken());
        }
        in.close();

        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++){
            adjList.add(new TreeSet<>());
        }
        for(int i = 0; i < n; i++){
            adjList.get(i + 1).add(report[i]);
        }
        for(int i = 1; i <= n; i++){
            dfs(i);
            System.out.print(result + " ");
            done = false;
            visited = new boolean[n + 1];
        }
    }
}