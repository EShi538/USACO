import java.io.*;
import java.util.*;
public class revegetate {
    static int n, m;
    static List<ArrayList<edge>> adjList = new ArrayList<>();
    static List<node> nodes = new ArrayList<>();
    static boolean[] visited;
    static int ans = 0;
    static boolean good = true;
    static void check(int node, int num){
        visited[node] = true;
        nodes.get(node).x = num;
        List<edge> adj = adjList.get(node);
        for(edge i: adj){
            if(!visited[i.b]){
                if(i.t == 'S'){
                    check(i.b, num);
                }
                else{
                    check(i.b, -num);
                }
                if(!good){
                    return;
                }
            }
            else{
                if((i.t == 'S' && nodes.get(i.b).x != nodes.get(node).x) || (i.t == 'D' && nodes.get(i.b).x == nodes.get(node).x)){
                    good = false;
                    return;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter out = new PrintWriter(new File("revegetate.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
            nodes.add(new node(i));
        }
        visited = new boolean[n + 1];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            char t = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new edge(b, t));
            adjList.get(b).add(new edge(a, t));
        }
        in.close();
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                ans++;
                check(i, 1);
                if(!good){
                    out.println(0);
                    out.close();
                    return;
                }
            }
        }
        StringBuilder print = new StringBuilder();
        for(int i = 0; i < ans; i++){
            print.append("0");
        }
        out.println("1" + print);
        out.close();
    }
}
class edge{
    int b;
    char t;
    public edge(int s, char t){
        this.b = s;
        this.t = t;
    }
}
class node{
    int x, a;
    public node(int x){
        this.x = x;
        this.a = 0;
    }
}