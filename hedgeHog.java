import java.io.*;
import java.util.*;
public class hedgeHog {
    static int n, k;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static List<Integer> longNodes = new ArrayList<>();
    static List<node> nodes = new ArrayList<>();
    static boolean[] visited;
    static int maxDist = -1, maxNode = -1, minNode = -1;
    static boolean done = false;
    static boolean valid = true;
    static int middleNode;
    static void bfs(int st){
        Queue<node> q = new LinkedList<>();
        q.add(nodes.get(st));
        visited[st] = true;
        while(!q.isEmpty()){
            node curr = q.poll();
            List<Integer> adj = adjList.get(curr.ID);
            for(int i: adj){
                if(!visited[i]){
                    visited[i] = true;
                    nodes.get(i).dist = curr.dist + 1;
                    q.add(nodes.get(i));
                }
            }
        }
    }
    static void dfs(int node, int target){
        if(node == target){
            done = true;
            return;
        }
        visited[node] = true;
        List<Integer> adj = adjList.get(node);
        for(int i: adj){
            if(!visited[i]){
                longNodes.add(i);
                dfs(i, target);
            }
            if(done){
                return;
            }
        }
        longNodes.remove(longNodes.size() - 1);
    }
    static boolean allHedgehogs(){
        for(int i = 1; i <= n; i++){
            if(adjList.get(i).size() == 1){
                continue;
            }
            if(i == middleNode){
                if(adjList.get(i).size() < 3){
                    return false;
                }
            }
            else {
                if (adjList.get(i).size() < 4) {
                    return false;
                }
            }
        }
        return true;
    }
    static void valid(int node, int parent, int length){
        List<Integer> adj = adjList.get(node);
        if(adj.size() == 1){
            if(length != k) {
                valid = false;
            }
            return;
        }
        for(int i: adj){
            if(i != parent){
                valid(i, node, length + 1);
                if (!valid) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++){
            nodes.add(new node(i));
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        in.close();
        visited = new boolean[n + 1];
        bfs(1);
        for(int i = 1; i <= n; i++){
            if(nodes.get(i).dist > maxDist){
                maxDist = nodes.get(i).dist;
                maxNode = i;
            }
        }
        visited = new boolean[n + 1];
        bfs(maxNode);
        maxDist = -1;
        for(int i = 1; i <= n; i++){
            if(nodes.get(i).dist > maxDist){
                maxDist = nodes.get(i).dist;
                minNode = i;
            }
        }
        visited = new boolean[n + 1];
        longNodes.add(minNode);
        dfs(minNode, maxNode);
        if(longNodes.size() % 2 == 0){
            out.println("No");
            out.close();
            return;
        }
        middleNode = longNodes.get(longNodes.size()/2);
        if(!allHedgehogs()){
            out.println("No");
            out.close();
            return;
        }
        visited = new boolean[n + 1];
        valid(middleNode, -1, 0);
        if(valid){
            out.println("Yes");
        }
        else{
            out.println("No");
        }
        out.close();
    }
}
class node{
    int ID, dist;
    public node(int ID){
        this.ID = ID;
        this.dist = 0;
    }
}
