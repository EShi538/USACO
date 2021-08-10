import java.io.*;
import java.util.*;
public class revegetate {
    static int n, m;
    static List<ArrayList<connection2>> adjList = new ArrayList<>();
    static boolean[] visited;
    static int[] assignments;
    static boolean works = true;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("revegetate.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            char type = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new connection2(b, type));
            adjList.get(b).add(new connection2(a, type));
        }
        in.close();
        visited = new boolean[n + 1];
        assignments = new int[n + 1];
        int cnt = 0;
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                works = true;
                dfs(i, 1);
                if(works){
                    cnt++;
                }                
            }
        }
        FileWriter out = new FileWriter("revegetate.out");
        if(cnt > 0){
            out.write(Integer.toString(1));
            for(int i = 0; i < cnt; i++){
                out.write(Integer.toString(0));
            }
        }
        else{
            out.write("0");
        }
        out.close();
    }
    static void dfs(int node, int num){
        visited[node] = true;
        assignments[node] = num;
        for(connection2 i: adjList.get(node)){
            if(!visited[i.node] && i.type == 'S'){
                dfs(i.node, num);
                if(!works){
                    return;
                }
            }
            else if(!visited[i.node] && i.type == 'D'){
                dfs(i.node, -num);
                if(!works){
                    return;
                }
            }
            else if(visited[i.node] && i.type == 'S' && assignments[i.node] != num){
                works = false;
                return;
            }
            else if(visited[i.node] && i.type == 'D' && assignments[i.node] == num){
                works = false;
                return;
            }
        }
    }
}
class connection2{
    int node;
    char type;
    public connection2(int node, char type){
        this.node = node;
        this.type = type;
    }
}