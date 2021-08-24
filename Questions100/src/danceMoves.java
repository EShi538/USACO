//http://usaco.org/index.php?page=viewproblem2&cpid=1086
import java.io.*;
import java.util.*;
public class danceMoves {
    static int n, k;
    static List<pair3> pairs = new ArrayList<>();
    static int[] cows;
    static List<HashSet<Integer>> beenTo = new ArrayList<>();
    static List<ArrayList<Integer>> cycles = new ArrayList<>(), adjList = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());
        cows = new int[n];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            pairs.add(new pair3(a, b));
        }
        in.close();
        for(int i = 0; i <= n; i++){
            beenTo.add(new HashSet<>());
            if(i > 0){
                beenTo.get(i).add(i);
            }
        }
        for(int i = 0; i < n; i++){
            cows[i] = i + 1;
        }
        simulate();
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n; i++){
            adjList.get(cows[i]).add(i + 1);
            adjList.get(i + 1).add(cows[i]);
        }
        visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                cycles.add(new ArrayList<>());
                dfs(i);
            }
        }
        int[] ans = new int[n + 1];
        for(List<Integer> i : cycles){
            Set<Integer> masterSet = new HashSet<Integer>();
            for(int j = 0; j < i.size(); j++){
                masterSet.addAll(beenTo.get(i.get(j)));
            }
            for(int j: i){
                ans[j] = masterSet.size();
            }
        }
        PrintWriter out = new PrintWriter(System.out);
        for(int i = 1; i <= n; i++){
            out.println(ans[i]);
        }
        out.close();
    }
    static void dfs(int node){
        visited[node] = true;
        cycles.get(cycles.size() - 1).add(node);
        for(int i: adjList.get(node)){
            if(!visited[i]){
                dfs(i);
            }
        }
    }
    static void simulate(){
        for(int i = 0; i < k; i++){
            int a = pairs.get(i).a - 1, b = pairs.get(i).b - 1;
            beenTo.get(cows[a]).add(b + 1); beenTo.get(cows[b]).add(a + 1);
            swap(a, b);
        }
    }
    static void swap(int a, int b){
        int tmp = cows[a];
        cows[a] = cows[b];
        cows[b] = tmp;
    }
}
class pair3{
    int a, b;
    public pair3(int a, int b){
        this.a = a;
        this.b = b;
    }
}