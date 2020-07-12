import java.io.*;
import java.util.*;
public class closing {
    static int n;
    static int m;
    static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static List<ArrayList<Integer>> parents = new ArrayList<ArrayList<Integer>>();
    static int[] perm;
    static boolean[] visited = new boolean[n + 1];
    static boolean[] closed = new boolean[n + 1];
    public static void DFS(int node){
        visited[node] = true;
        ArrayList<Integer> adj = adjList.get(node);
        for(int i: adj){
            if(visited[i] == false && closed[i] == false){
                DFS(i);
            }
        }
        return;
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("closing.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());      
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        perm = new int[n];
        for(int i = 0; i < n; i++){
            perm[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        File out = new File("closing.out");
        FileWriter writer = new FileWriter(out);
        closed = new boolean[n + 1];
        for(int i = 0; i < n; i++){
            visited = new boolean[n + 1];
            DFS(perm[i]);
            boolean good = true;
            for(int j = 1; j <= n; j++){
                if(visited[j] == false && closed[j] == false){
                    good = false;
                    break;
                }
            }
            if(good == true){
                writer.write("YES" + "\n");
            }
            else{
                writer.write("NO" + "\n");
            }
            closed[perm[i]] = true;
        }
        writer.close();
    }
}