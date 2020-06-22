import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int m = in.nextInt();
    static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static boolean[] visited = new boolean[n + 1];

    public static int BFS(int st, int en){
        Queue<node> q = new LinkedList<node>();
        q.add(new node(st, 0));
        visited[st] = true;
        while(!q.isEmpty()){
            node node = q.poll();
            ArrayList<Integer> adj = adjList.get(node.ID);
            if(node.ID == en){
                return node.dist;
            }
            for(int i = 0; i < adj.size(); i++){
                int nNode = adj.get(i);
                if(visited[nNode] == false){
                    q.add(new node(nNode, node.dist + 1));
                    visited[nNode] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        adjList.add(new ArrayList<Integer>());
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<Integer>());
        }
        for(int i = 1; i <= m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        List<node> arr = new ArrayList<node>();
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            int dist = BFS(1, i);
            arr.add(new node(i, dist));
            if(dist > max){
                max = dist;
            }
            for(int j = 1; j <= n; j++){
                visited[j] = false;
            }
        }

        List<Integer> best = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            if(arr.get(i).dist == max){
                best.add(arr.get(i).ID);
            }
        }
        Collections.sort(best);
        System.out.println(best.get(0) + " " + max + " " + best.size());
    }
}

class node{
    int ID;
    int dist;
    public node(int ID, int dist){
        this.ID = ID;
        this.dist = dist;
    }
}

class sortDist implements Comparator<node> { 
    public int compare(node a, node b) { 
        return b.dist - a.dist; 
    } 
} 