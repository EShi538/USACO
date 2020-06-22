import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static List<cow> cows = new ArrayList<cow>();
    static List<ArrayList<cow>> adjList = new ArrayList<ArrayList<cow>>();
    static boolean[] visited = new boolean[n];
    static int count = 1;

    public static void DFS(cow node){
        visited[node.ID] = true;
        ArrayList<cow> adj = adjList.get(node.ID);
        for(int i = 0; i < adj.size(); i++){
            cow nNode = adj.get(i);
            if(visited[nNode.ID] == false && distance(node.x, node.y, nNode.x, nNode.y) <= Math.pow(node.signal, 2)){
                count++;
                DFS(nNode);
            }
        }
        return;
    }

    public static int distance(int x, int y, int x1, int y1){
        return (int)(Math.pow((Math.abs(x - x1)), 2) + Math.pow((Math.abs(y - y1)), 2));
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < n; i++){
            cows.add(new cow(i, in.nextInt(), in.nextInt(), in.nextInt()));
        }
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<cow>());
            for(int j = 0; j < n; j++){
                if(i == j){
                    continue;
                }
                adjList.get(i).add(new cow(j, cows.get(j).x, cows.get(j).y, cows.get(j).signal));
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            cow curr = cows.get(i);
            DFS(curr);
            visited = new boolean[n];
            if(count > max){
                max = count;
            }
            count = 1;
        }
        System.out.println(max);
    }
}

class cow{
    int ID;
    int x;
    int y;
    int signal;
    public cow(int ID, int x, int y, int signal){
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.signal = signal;
    }
}