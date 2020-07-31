import java.io.*;
import java.math.*;
import java.util.*;

public class journey {
    static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static boolean[] visited;
    static int[] lengths;
    static BigDecimal[] probs;
    static void dfs(int node, int cnt){
        visited[node] = true;
        List<Integer> adj = adjList.get(node);
        lengths[node] = cnt;
        for(int i: adj){
            if(!visited[i]){
                dfs(i, cnt + 1);
            }
        }
    }

    static void dfs1(int node, BigDecimal prob){
        visited[node] = true;
        List<Integer> adj =  adjList.get(node);
        probs[node] = prob;
        for(int i: adj){
            if(!visited[i]){
                BigDecimal test;
                if(node == 1) {
                    test = BigDecimal.ONE.divide(BigDecimal.valueOf(adj.size()), 13, RoundingMode.HALF_UP);
                }
                else{
                    test = BigDecimal.ONE.divide(BigDecimal.valueOf((adj.size() - 1)), 13, RoundingMode.HALF_UP);
                }
                dfs1(i, prob.multiply(test, new MathContext(10)));
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        visited = new boolean[n + 1];
        lengths = new int[n + 1];
        probs = new BigDecimal[n + 1];
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        in.close();

        dfs(1, 0);
        visited = new boolean[n + 1];
        dfs1(1, BigDecimal.ONE);
        BigDecimal ans = BigDecimal.ZERO;
        for(int i = 1; i <= n; i++){
            if(adjList.get(i).size() == 1){
                ans = ans.add(BigDecimal.valueOf(lengths[i]).multiply(probs[i]), new MathContext(13));
            }
        }
        System.out.println(ans);
    }
}
