import java.io.*;
import java.util.*;
public class wormhole {
    static int n;
    static List<coordinate> coordinates = new ArrayList<>();
    static HashMap<Integer, List<Integer>> pointMap = new HashMap<>();
    static boolean[] wentThrough = new boolean[n], visited = new boolean[n], type = new boolean[n];
    static List<HashSet<Integer>> adjList = new ArrayList<>();
    static int ans;
    static boolean hasCycle = false;
    static boolean done = false;
    static int[] pair;
    static boolean teleported = false;
    static int cnt = 0;
    static void solve(){
        if(everyonePaired()){  
            for(int i = 0; i < n; i++){
                adjList.set(i, new HashSet<>());
            }
            for(int i = 0; i < n; i++){
                adjList.get(i).add(pair[i]);
                adjList.get(pair[i]).add(i);
            }
            System.out.println(cnt);
            if(check()){
                cnt++;      
                for(int i = 0; i < n; i++){
                    System.out.print("{" + i + ", " + pair[i] + "}");
                }
                System.out.println();
                ans++;
            }
            return;
        }
        int i;
        for(i = 0; i < n; i++){
            if(pair[i] == -1){
                break;
            }
        }
        for(int j = i + 1; j < n; j++){
            if(pair[j] == -1){
                pair[i] = j;
                pair[j] = i;
                solve();
                pair[i] = -1;
                pair[j] = -1;
            }
        }
    }
    static boolean everyonePaired(){
        for(int i: pair){
            if(i == -1){
                return false;
            }
        }
        return true;
    }
    static boolean check(){
        for(int i = 0; i < n; i++){
            done = false;
            hasCycle = false;
            visited = new boolean[n];
            type = new boolean[n];
            teleported = false;
            findCycle(i);
            if(hasCycle){
                return true;
            }
        }
        return false;
    }
    static void findCycle(int node){
        visited[node] = true;
        if(teleported){
            type[node] = true;
        }
        else{
            type[node] = false;
        }
        Set<Integer> adj = adjList.get(node);
        if(teleported){
            int y = coordinates.get(node).y;
            int pos = Collections.binarySearch(pointMap.get(y), node);
            if(pos == pointMap.get(y).size() - 1){
                done = true;
                return;
            }
            int newPoint = pointMap.get(y).get(pos + 1);
            if(!visited[newPoint] || type[newPoint] == type[node]){
                teleported = false;
                findCycle(newPoint);
                if(done){
                    return;
                }
            }
            else{
                hasCycle = true;
                done = true;
                return;
            }
        }
        else{
            for(int i: adj){
                if(!visited[i] || type[i] == type[node]){
                    teleported = true;
                    findCycle(i);
                    if(done){
                        return;
                    }
                }
                else {
                    hasCycle = true;
                    done = true;
                    return;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out =new PrintWriter(new File("wormhole.out"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            adjList.add(new HashSet<>());
        }
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            coordinates.add(new coordinate(x, y));
        }
        in.close();
        coordinates.sort(new sort());
        for(int index = 0; index < n; index++){
            coordinate i = coordinates.get(index);
            List<Integer> l;
            l = (pointMap.containsKey(i.y)) ? pointMap.get(i.y) : new ArrayList<>();
            l.add(index);
            pointMap.put(i.y, l);
        }
        //find some way to go through the pairs
        pair = new int[n];
        for(int i = 0; i < n; i++){
            pair[i] = -1;
        }
        solve();
        System.out.println(cnt);
        out.println(ans);
        out.close();
    }
}
class coordinate{
    int x, y;
    public coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class sort implements Comparator<coordinate>{
    public int compare(coordinate a, coordinate b){
        if(a.y != b.y){
            return a.y - b.y;
        }
        else{
            return a.x - b.x;
        }
    }
}