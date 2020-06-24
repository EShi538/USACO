import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int m = in.nextInt();
    static List<ArrayList<connection>> adjList = new ArrayList<ArrayList<connection>>();
    static List<pasture> pasList = new ArrayList<pasture>();
    static boolean[] visited = new boolean[n + 1];
    static boolean bad = false;

    public static void floodfill(int node, int grass){
        visited[node] = true;
        ArrayList<connection> adj = adjList.get(node);
        pasList.get(node).grassType = grass;
        for(int i = 0; i < adj.size(); i++){
            connection nNode = adj.get(i);
            if(visited[nNode.node] == false && nNode.type == 'S'){
                floodfill(nNode.node, grass);
            }
            else if(visited[nNode.node] == false && nNode.type == 'D'){
                floodfill(nNode.node, -grass);
            }
            else if(visited[nNode.node] == true){
                if(nNode.type == 'S' && grass != pasList.get(nNode.node).grassType){
                    bad = true;
                    return;
                }
                else if(nNode.type == 'D' && grass == pasList.get(nNode.node).grassType){
                    bad = true;
                    return;
                }
            }
        }
        return;
    }

    public static void main(String[] args) throws Exception {
        pasList.add(new pasture(-1));
        for(int i = 1; i <= n; i++){
            pasList.add(new pasture(i));
        }
        for(int i = 0 ; i <= n; i++){
            adjList.add(new ArrayList<connection>());
        }
        for(int i = 0; i < m; i++){
            char type = in.next().charAt(0);
            int a = in.nextInt();
            int b = in.nextInt();
            adjList.get(a).add(new connection(b, type));
            adjList.get(b).add(new connection(a, type));
        }
        int cnt = 0;
        for(int i = 1; i <= n; i++){
            if(pasList.get(i).grassType == 0){
                cnt++;
                floodfill(pasList.get(i).ID, 1);
            }
        }
        String out = "";
        if(bad == false){
            out = out + "1";
            for(int i = 0; i < cnt; i++){
                out = out + "0";
            }
            System.out.println(out);
        }
        else{
            System.out.println(0);
        }
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

class pasture{
    int ID;
    int grassType; //1 or -1
    public pasture(int ID){
        this.ID = ID;
        this.grassType = 0;
    }
}