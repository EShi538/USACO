import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class wormsort {
    static List<ArrayList<connection>> adjList = new ArrayList<ArrayList<connection>>();
    static boolean[] visited = new boolean[0];
    static List<position> nodes = new ArrayList<position>();
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("wormsort.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        int[] cows = new int[n + 1];
        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= n; i++){
            cows[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<connection>());
        }
        int max = -1;
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(w > max){
                max = w;
            }
            adjList.get(a).add(new connection(b, w));
            adjList.get(b).add(new connection(a, w));
        }
        in.close();

        File out = new File("wormsort.out");
        FileWriter writer = new FileWriter(out);
        boolean good = true;
        for(int i = 1; i <= n; i++){
            if(cows[i] != i){
                good = false;
                break;
            }
        }
        if(good == true){
            writer.write("-1");
            writer.close();
            return;
        }

        nodes.add(new position(0));
        for(int i = 1; i <= n; i++){
            nodes.add(new position(i));
        }
        int lb = 0; int ub = max;
        int best = -1;
        while(lb < ub){
            int mid = (lb + ub)/2;
            int currReg = 1;
            for(int i = 1; i <= n; i++){
                if(nodes.get(i).reg == -1){
                    floodfill(nodes.get(i).ID, mid, currReg);
                    currReg++;
                }
            }
            boolean good1 = true;
            for(int i = 1; i <= n; i++){
                if(nodes.get(cows[i]).reg != nodes.get(i).reg && cows[i] != i){
                    good1 = false;
                    break;
                }
            }
            if(good1 == true){
                if(mid > best){
                    best = mid;
                }
                lb = mid + 1;
            }
            else{
                ub = mid;
            }
            for(int i = 1; i <= n; i++){
                nodes.get(i).reg = -1;
            }
            visited = new boolean[n + 1];
        }
        writer.write(Integer.toString(best));
        writer.close();
    }
    public static void floodfill(int start, int mid, int reg){
        Stack<Integer> st = new Stack<Integer>();
        st.push(start);
        while(!st.isEmpty()){
            int node = st.pop();
            visited[node] = true;
            nodes.get(node).reg = reg;
            ArrayList<connection> adj = adjList.get(node);
            for(int i = 0; i < adj.size(); i++){
                if(adj.get(i).width >= mid && visited[adj.get(i).node] == false){
                    st.push(adj.get(i).node);
                }
            }
        }
        return;
    }
}

class connection{
    int node;
    int width;
    public connection(int node, int width){
        this.node = node;
        this.width = width;
    }
}

class position{
    int ID;
    int reg;
    public position(int ID){
        this.ID = ID;
        this.reg = -1;
    }
}