import java.io.*;
import java.util.*;
public class bipartite {
    static int n;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static List<node> nodes = new ArrayList<>();
    static boolean[] visited;
    static void color(int node){
        visited[node] = true;
        List<Integer> adj = adjList.get(node);
        for(int i: adj){
            if(!visited[i]){
                nodes.get(i).color = -nodes.get(node).color;
                color(i);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
            nodes.add(new node(i));
        }
        for(int i = 0; i < n - 1; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        in.close();

        color(1);
        long Acnt = 0, Bcnt = 0;
        for(node i: nodes){
            if(i.color == -1){
                Bcnt++;
            }
            else{
                Acnt++;
            }
        }
        Acnt--;
        System.out.println((Acnt * Bcnt) - (n - 1));
    }
}

class node{
    int ID, color;
    public node(int ID){
        this.ID = ID;
        this.color = 1;
    }
}