import java.io.*;
import java.util.*;
public class App {
    static int n;
    static List<TreeSet<Integer>> adjList = new ArrayList<>();
    static List<node> nodes = new ArrayList<>();
    static int[] moves;
    static void findParents(int parent, int node){
        TreeSet<Integer> adj = adjList.get(node);
        nodes.get(node).parent = parent;
        for(int i: adj){
            if(i != parent){
                findParents(node, i);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i <= n; i++){
            nodes.add(new node(i));
            adjList.add(new TreeSet<>());
        }
        for(int i = 0; i < n - 1; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        moves = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            moves[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        findParents(-1, 1);
        int p2 = 0;
        for(int i = 2; i <= n; i++){
            adjList.get(i).remove(nodes.get(i).parent);
        }
        for(int i = 0; i < n; i++){
            if(p2 >= n){
                break;
            }
            TreeSet<Integer> next = new TreeSet<Integer>();
            for(int j = 0; j < adjList.get(moves[i]).size(); j++){
                p2++;
                next.add(moves[p2]);
            }
            if(!next.equals(adjList.get(moves[i]))){
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}

class node{
    int ID, parent;
    public node(int ID){
        this.ID = ID;
        this.parent = 0;
    }
}