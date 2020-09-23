import java.io.*;
import java.util.*;
public class swap {
    static int n, m, k;
    static int[] a;
    static List<ArrayList<Integer>> adjList = new ArrayList<>(), cycles = new ArrayList<>();
    static boolean done = false;
    static boolean[] visited;
    static void findCycle(int node, int ind) {
        cycles.get(ind).add(node);
        visited[node] = true;
        List<Integer> adj = adjList.get(node);
        for(int i: adj){
            if(!visited[i]){
                findCycle(i, ind);
                if(done){
                    return;
                }
            }
            else{
                done = true;
                return;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("swap.in"));
        PrintWriter out = new PrintWriter(new File("swap.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n];
        for(int i = 1; i <= n; i++){
            a[i - 1] = i;
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            rev(l - 1, r - 1);
        }
        in.close();
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n; i++){
            adjList.get(a[i]).add(i + 1);
        }
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                cycles.add(new ArrayList<>());
                findCycle(i, cycles.size() - 1);
            }
        }
        int[] cycleInd = new int[n + 1], posInCycle = new int[n + 1];
        for(int i = 0; i < cycles.size(); i++){
            for(int j = 0; j < cycles.get(i).size(); j++){
                cycleInd[cycles.get(i).get(j)] = i;
                posInCycle[cycles.get(i).get(j)] = j;
            }
        }
        int[] ans = new int[n];
        for(int i = 1; i <= n; i++){
            int index = (posInCycle[i] + k) % cycles.get(cycleInd[i]).size();
            ans[cycles.get(cycleInd[i]).get(index) - 1] = i;
        }
        for(int i: ans){
            out.println(i);
        }
        out.close();
    }
    static void rev(int l, int r){
        for(int i = l; i <= (l + r)/2; i++){
            int tmp = a[i];
            a[i] = a[r - i + l];
            a[r - i + l] = tmp;
        }
    }
}
