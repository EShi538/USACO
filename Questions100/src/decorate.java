import java.io.*;
import java.util.*;
public class decorate{
    static int n, m;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static boolean[] visited, visitedTemp;
    static int[] assignments;
    static boolean works = true;
    static int jCnt = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("decorate.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b); adjList.get(b).add(a);
        }
        in.close();
        visited = new boolean[n + 1];
        visitedTemp = new boolean[n + 1];
        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                jCnt = 0;
                works = true;
                assignments = new int[n + 1];
                visitedTemp = new boolean[n + 1];
                dfs(i, 1, i, -1);
                if(!works){
                    ans = -1;
                    break;
                }
                int jCnt1 = jCnt;
                jCnt = 0;
                works = true;
                assignments = new int[n + 1];
                visitedTemp = new boolean[n + 1];
                dfs(i, -1, i, -1);
                if(!works){
                    ans = -1;
                    break;
                }
                int jCnt2 = jCnt;
                ans += Math.max(jCnt1, jCnt2);
            }
        }
        FileWriter out = new FileWriter("decorate.out");
        out.write(Integer.toString(ans));
        out.close();
    }
    static void dfs(int node, int num, int starting, int prevNode){ //1 = F, -1 = J
        visited[node] = true;
        visitedTemp[node] = true;
        assignments[node] = num;
        if(num == -1){
            jCnt++;
        }
        for(int i: adjList.get(node)){
            if(!visitedTemp[i]){
                dfs(i, -num, starting, node);
                if(!works){
                    return;
                }
            }
            else if(visitedTemp[i] && assignments[i] != -num && (i != starting || i != prevNode)){
                works = false;
                return;
            }
        }
    }
}