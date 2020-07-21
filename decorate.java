import java.io.*;
import java.util.*;
public class decorate {
    static int n;
    static int m;
    static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static List<Character> assign = new ArrayList<Character>();
    static boolean[] visited;
    static boolean bad = false;
    static int Jcnt = 0;
    static int Fcnt = 0;
    static void fill(int node, int prevChar){
        visited[node] = true;
        if(prevChar == 'J') {
            Fcnt++;
            assign.set(node, 'F');
        }
        else{
            Jcnt++;
            assign.set(node, 'J');
        }
        ArrayList<Integer> adj = adjList.get(node);
        for(int i: adj){
            if(!visited[i]){
                fill(i, assign.get(node));
                if(bad){
                    return;
                }
            }
            else{
                if(assign.get(i) == assign.get(node)){
                    bad = true;
                    return;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("decorate.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<Integer>());
            assign.add('X');
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        File out = new File("decorate.out");
        FileWriter writer = new FileWriter(out);
        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                fill(i, 'F');
                if(bad){
                    writer.write("-1");
                    writer.close();
                    return;
                }
                ans = ans + Math.max(Fcnt, Jcnt);
                Fcnt = 0;
                Jcnt = 0;
            }
        }
        writer.write(Integer.toString(ans));
        writer.close();
    }
}
