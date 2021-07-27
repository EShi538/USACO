import java.util.*;
import java.io.*;

public class moocast {
    static int n;
    static List<cow> cows = new ArrayList<cow>();
    static List<ArrayList<cow>> adjList = new ArrayList<ArrayList<cow>>();
    static boolean[] visited;
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
        BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
        FileWriter out = new FileWriter("moocast.out");
        n = Integer.parseInt(in.readLine());
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()); 
            int c = Integer.parseInt(st.nextToken());
            cows.add(new cow(i, a, b, c));
        }
        in.close();
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
        out.write(Integer.toString(max));
        out.close();
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