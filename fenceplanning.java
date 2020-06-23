import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt(); //number of cows
    static int m = in.nextInt(); //pairs of cows that moo to each other in the same network
    static List<cow> cows = new ArrayList<cow>(); //list of cows
    static List<ArrayList<cow>> adjList = new ArrayList<ArrayList<cow>>(); //adjacency list
    static boolean[] visited = new boolean[n + 1]; //base 1 (cow 1 = index 1)

    public static void floodfill(cow st, int net){
        visited[st.ID] = true;
        cows.get(st.ID - 1).networkID = net;
        ArrayList<cow> adj = adjList.get(st.ID);
        for(int i = 0; i < adj.size(); i++){
            cow ncow = adj.get(i);
            if(visited[ncow.ID] == false){
                floodfill(ncow, net);
            }
        }
        return;
    }
    
    public static void main(String[] args) throws Exception {
        //input all cows into cow array
        for(int i = 1; i <= n; i++){
            cows.add(new cow(in.nextInt(), in.nextInt(), i));
        }
        adjList.add(new ArrayList<cow>());
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<cow>());
        }
        Collections.sort(cows, new IDsort()); //for easier searching (to find cow 1, go to index 0 (1 - 1))

        //input connections into adjacency list
        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            adjList.get(a).add(new cow(cows.get(b - 1).x, cows.get(b - 1).y, cows.get(b - 1).ID));
            adjList.get(b).add(new cow(cows.get(a - 1).x, cows.get(a - 1).y, cows.get(a - 1).ID));
        }

        int netID = 1;
        //loop through all cows; floodfill as needed
        for(int i = 0; i < n; i++){
            if(cows.get(i).networkID == 0){
                floodfill(cows.get(i), netID);
                netID++;
            }
        }
        
        Collections.sort(cows, new Netsort());

        int min = Integer.MAX_VALUE;
        //process newfound data and output
        //find the max/min x/y coordinates of each network
        for(int i = 0; i < n; i++){
            List<Integer> sameNetx = new ArrayList<Integer>();
            List<Integer> sameNety = new ArrayList<Integer>();
            int j = i;
            while(j < n - 1 && cows.get(j).networkID == cows.get(j + 1).networkID){
                sameNetx.add(cows.get(j).x);
                sameNety.add(cows.get(j).y);
                j++;
            }
            i = j;
            sameNetx.add(cows.get(j).x);
            sameNety.add(cows.get(j).y);
            //find max/min x/y of that list
            int minX = Collections.min(sameNetx);
            int maxX = Collections.max(sameNetx);
            int minY = Collections.min(sameNety);
            int maxY = Collections.max(sameNety);
            int perimeter = (2 * (Math.abs(maxX - minX))) + (2 * (Math.abs(maxY - minY)));
            if(perimeter < min){
                min = perimeter;
            }
        }
        System.out.println(min);
    }
}

class cow{
    int x;
    int y;
    int ID;
    int networkID; //the network ID of this cow (used to do a floodfill)
    public cow(int x, int y, int ID){
        this.x = x;
        this.y = y;
        this.ID = ID;
    }
}

class IDsort implements Comparator<cow> { 
    public int compare(cow a, cow b) { 
        return a.ID - b.ID; 
    } 
} 

class Netsort implements Comparator<cow> {
    public int compare(cow a, cow b){
        return a.networkID - b.networkID;
    }
}