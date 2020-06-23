import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int q = in.nextInt();
    static List<ArrayList<connection>> adjList = new ArrayList<ArrayList<connection>>(); 
    static boolean[] visited = new boolean[n + 1];
    static int nodeCnt = 0;

    public static void DFS(int node, int relevance){
        visited[node] = true;
        ArrayList<connection> adj = adjList.get(node);
        for(int i = 0; i < adj.size(); i++){
            connection nNode = adj.get(i);
            if(nNode.relevance >= relevance && visited[nNode.ID] == false){
                nodeCnt++;
                DFS(nNode.ID, relevance);
            }
        }
        return;
    }

    public static void main(String[] args) throws Exception {
        adjList.add(new ArrayList<connection>());
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<connection>());
        }
        for(int i = 0; i < n - 1; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int relevance = in.nextInt();
            adjList.get(a).add(new connection(b, relevance));
            adjList.get(b).add(new connection(a, relevance));
        }
        for(int i = 0; i < q; i++){
            int k = in.nextInt();
            int st = in.nextInt();
            DFS(st, k);
            System.out.println(nodeCnt);
            nodeCnt = 0;
            visited = new boolean[n + 1];
        }
    }
}

class connection{
    int ID;
    int relevance;
    public connection(int ID, int relevance){
        this.ID = ID;
        this.relevance = relevance;
    }
}
