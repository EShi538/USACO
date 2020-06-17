import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int N = in.nextInt();
    static int M = in.nextInt();
    static int[][] adjMatrix = new int[N][N];
    static boolean[] visited = new boolean[N];
    static List<island> storage = new ArrayList<island>();

    public static void BFS (){
        Queue<island> q = new LinkedList<island>();
        q.add(new island(M, 0));
        visited[M - 1] = true;
        storage.add(new island(M, 0));
        while(!q.isEmpty()){
            island root = q.poll();
            int[] row = adjMatrix[root.ID - 1];
            List<Integer> connected = new ArrayList<Integer>();
            for(int i = 0; i < N; i++){
                if(row[i] == 1){
                    connected.add(i);
                }
            }
            for(int i = 0; i < connected.size(); i++){
                int curr = connected.get(i);
                if(visited[curr] == false){
                    q.add(new island(curr + 1, root.depth + 1));
                    storage.add(new island(curr + 1, root.depth + 1));
                    visited[curr] = true;
                }
            }
            connected.removeAll(connected);
        }    
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                adjMatrix[i][j] = in.nextInt();
            }
        }
        BFS();   
        Collections.sort(storage, new SortDepth()); 
        List<Integer> type = new ArrayList<Integer>();
        int j = 0;
        while(j < storage.size()){
            if(j == storage.size() - 1){
                System.out.print(storage.get(storage.size() - 1).ID);
                break;
            }
            while(storage.get(j + 1).depth == storage.get(j).depth){
                type.add(storage.get(j).ID);
                j++;
                if(j == storage.size() - 1){
                    break;
                }
            }
            type.add(storage.get(j).ID);
            j++;
            Collections.sort(type);
            for(int i = 0; i < type.size(); i++){
                System.out.print(type.get(i) + " ");
            }
            type.removeAll(type);
            System.out.println();
        }
        in.close();
    }
}

class island{
    int ID;
    int depth;
    public island(int ID, int depth){
        this.ID = ID;
        this.depth = depth;
    }
}

class SortDepth implements Comparator<island> { 
    public int compare(island a, island b) { 
        return a.depth - b.depth; 
    } 
} 