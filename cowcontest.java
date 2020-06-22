import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int m = in.nextInt();
    static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static List<ArrayList<Integer>> adjListRev = new ArrayList<ArrayList<Integer>>();
    static boolean[] visited = new boolean[n + 1];
    static boolean[] visitedRev = new boolean[n + 1];
    static int cnt = 0;
    static int cntPar = 0;
    static int cntChild = 0;

    public static void numChildren (int node){
        visited[node] = true;
        ArrayList<Integer> adj = adjList.get(node);
        for(int i = 0; i < adj.size(); i++){
            int nNode = adj.get(i);
            if(visited[nNode] == false){
                cntChild = cntChild + 1;
                numChildren(nNode);
            }
        }
        return;
    }

    public static void numParents (int node){
        visitedRev[node] = true;
        ArrayList<Integer> adj = adjListRev.get(node);
        for(int i = 0; i < adj.size(); i++){
            int nNode = adj.get(i);
            if(visitedRev[nNode] == false){
                cntPar = cntPar + 1;
                numParents(nNode);
            }
        }
        return;
    }
    public static void main(String[] args) throws Exception {
        adjList.add(new ArrayList<Integer>());
        adjListRev.add(new ArrayList<Integer>());
        for(int i = 1; i <= n; i++){
            adjList.add(new ArrayList<Integer>());
            adjListRev.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            adjList.get(a).add(b);
            adjListRev.get(b).add(a);
        }
        for(int i = 1; i <= n; i++){
            numChildren(i);
            numParents(i);
            if(cntPar + cntChild == n - 1){
                cnt++;
            }
            for(int j = 0; j < visited.length; j++){
                visited[j] = false;
            }
            for(int j = 0; j < visitedRev.length; j++){
                visitedRev[j] = false;
            }
            cntPar = 0;
            cntChild = 0;
        }
        System.out.println(cnt);
    }
}