import java.io.*;
import java.util.*;
public class meeting {
    static int n, m;
    static List<ArrayList<connection>> adjList = new ArrayList<>();
    static List<Integer> bessieTimes = new ArrayList<>();
    static List<Integer> elsieTimes = new ArrayList<>();
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;
    static void bessieDfs(int node, int bessieTime){
        visited[node] = true;
        List<connection> adj = adjList.get(node);
        if(node == n){
            bessieTimes.add(bessieTime);
            return;
        }
        for(connection i: adj){
            if(!visited[i.node]){
                bessieDfs(i.node, bessieTime + i.bessieTime);
                visited[i.node] = false;
            }
        }
    }
    static void elsieDfs(int node, int elsieTime){
        visited[node] = true;
        List<connection> adj = adjList.get(node);
        if(node == n){
            elsieTimes.add(elsieTime);
            return;
        }
        for(connection i: adj){
            if(!visited[i.node]){
                elsieDfs(i.node, elsieTime + i.elsieTime);
                visited[i.node] = false;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("meeting.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        File out = new File("meeting.out");
        PrintWriter writer = new PrintWriter(out);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int bessieTime = Integer.parseInt(st.nextToken());
            int elsieTime = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new connection(b, bessieTime, elsieTime));
        }
        in.close();

        bessieDfs(1, 0);
        visited = new boolean[n + 1];
        elsieDfs(1, 0);
        Collections.sort(bessieTimes);
        Collections.sort(elsieTimes);
        for(int i: bessieTimes){
            int pos = Collections.binarySearch(elsieTimes, i);
            if(pos >= 0){
                ans = Math.min(ans, i);
            }
        }
        if(ans == Integer.MAX_VALUE){
            writer.write("IMPOSSIBLE");
            writer.close();
            return;
        }
        writer.println(ans);
        writer.close();
    }
}

class connection{
    int node, bessieTime, elsieTime;
    public connection(int node, int bessieTime, int elsieTime){
        this.node = node; this.bessieTime = bessieTime; this.elsieTime = elsieTime;
    }
}