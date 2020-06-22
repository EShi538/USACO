import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int k = in.nextInt();
    static int n = in.nextInt();
    static int m = in.nextInt();
    static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static List<Integer> cowPos = new ArrayList<Integer>();
    static boolean[] visited = new boolean[n + 1];
    static List<node> nodes = new ArrayList<node>();

    public static void DFS(int st){
        visited[st] = true;
        nodes.get(st - 1).reachable++;
        ArrayList<Integer> adj = adjList.get(st);
        for(int i = 0; i < adj.size(); i++){
            int node = adj.get(i);
            if(visited[node] == false){
                DFS(node);
            }
        }
        return;
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < k; i++){
            cowPos.add(in.nextInt());
        }
        adjList.add(new ArrayList<Integer>());
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            adjList.get(a).add(b);
        }
        for(int i = 1; i <= n; i++){
            nodes.add(new node(i, 0));
        }

        for(int i = 0; i < k; i++){
            int cPos = cowPos.get(i);
            DFS(cPos);
            visited = new boolean[n + 1];
        }

        int cnt = 0;
        for(int i = 0; i < n; i++){
            if(nodes.get(i).reachable == k){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

class node{
    int ID;
    int reachable;
    public node(int ID, int reachable){
        this.ID = ID;
        this.reachable= reachable;
    }
}
