import java.io.*;
import java.util.*;
public class truth {
    static List<ArrayList<connection>> adjList = new ArrayList<>();
    static List<relationship> relationships = new ArrayList<>();
    static List<node> nodes = new ArrayList<>();
    static boolean[] visited;
    static boolean good = true;
    static TreeSet<Integer> wentThrough = new TreeSet<>();
    static void floodfill(node node){
        List<connection> adj = adjList.get(node.node);
        wentThrough.add(node.node);
        visited[node.node] = true;
        for(connection i: adj){
            if(!visited[i.node]){
                if(i.type == 'T'){
                    nodes.get(i.node).assignment = node.assignment;
                }
                else{
                    nodes.get(i.node).assignment = -node.assignment;
                }
                floodfill(nodes.get(i.node));
                if(!good){
                    return;
                }
            }
            else{
                if(i.type == 'T'){
                    if(node.assignment != nodes.get(i.node).assignment){
                        good = false;
                        return;
                    }
                }
                else{
                    if(-node.assignment != nodes.get(i.node).assignment){
                        good = false;
                        return;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("truth.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            char type = st.nextToken().charAt(0);
            relationships.add(new relationship(a, new connection(b, type)));
        }
        in.close();

        int lb = 0;
        int ub = m + 1;
        for(int i = 0; i <= n; i++){
            nodes.add(new node(i, 0));
        }
        int ans = -1;
        while(lb != ub){
            int guess = (lb + ub)/2;
            if(guess == 8){
                int x = 0;
            }
            for(ArrayList<connection> i: adjList){
                i.clear();
            }
            Set<Integer> relevantNodes = new TreeSet<>();
            for(int i = 0; i < guess; i++){
                adjList.get(relationships.get(i).a).add(relationships.get(i).other);
                relevantNodes.add(relationships.get(i).a); relevantNodes.add(relationships.get(i).other.node);
            }
            boolean bad = false;
            for(int i: relevantNodes){
                if(adjList.get(i).size() == 0){
                    continue;
                }
                if(!visited[i]){
                    wentThrough.clear();
                    nodes.get(i).assignment = 1;
                    floodfill(nodes.get(i));
                    if(!good){
                        good = true;
                        for(int j: wentThrough){
                            visited[j] = false;
                        }
                        nodes.get(i).assignment = -1;
                        floodfill(nodes.get(i));
                    }
                }
                if(!good){
                    bad = true;
                    ub = guess;
                    break;
                }
            }
            if(bad){
                visited = new boolean[n + 1];
                good = true;
                nodes.clear();
                for(int i = 0; i <= n; i++){
                    nodes.add(new node(i, 0));
                }
                continue;
            }
            if (guess > ans) {
                ans = guess;
            }
            lb = guess + 1;
            visited = new boolean[n + 1];
            good = true;
            nodes.clear();
            for(int i = 0; i <= n; i++){
                nodes.add(new node(i, 0));
            }
        }
        File out = new File("truth.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}

class relationship{
    int a;
    connection other;
    public relationship(int a, connection other){
        this.a = a;
        this.other = other;
    }
}
class connection{
    int node;
    char type;
    public connection(int node, char type){
        this.node = node;
        this.type = type;
    }
}

class node{
    int node;
    int assignment;
    public node(int node, int assignment){
        this.node = node;
        this.assignment = assignment;
    }
}