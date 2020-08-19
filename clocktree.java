import java.io.*;
import java.util.*;
public class clocktree {
    static int n;
    static int[] times;
    static int[] clone;
    static boolean[] visited;
    static List<node> nodes = new ArrayList<>();
    static List<TreeSet<Integer>> adjList = new ArrayList<>();
    static Set<Integer> finished = new HashSet<>();
    static boolean done = false, good = false;
    static void solve(int node){
        clone[node] = (clone[node] + 1) % 12;
        visited[node] = true;
        TreeSet<Integer> check = adjList.get(node);
        TreeSet<Integer> adj = new TreeSet<>();
        for(int i: check){
            if(!finished.contains(i)){
                adj.add(i);
            }
        }
        if(finished.size() == n - 2){
            int a = (clone[node] == 0) ? 12 : clone[node];
            int b = (clone[adj.first()] == 0) ? 12 : clone[adj.first()];
            if(a != 12 && b != 12) {
                if ((a == b) || (a - b == 1)) {
                    good = true;
                }
            }
            else if(a == 1){
                good = true;
            }
            else if(a == 12 && b == 11){
                good = true;
            }
            else if(a == 12 && b == 12){
                good = true;
            }
            done = true;
            return;
        }
        if(adj.size() == 1){
            clone[adj.first()] = (clone[adj.first()] + (12 - clone[node])) % 12;
            clone[node] = 0;
            finished.add(node);
        }
        for(int i: adj){
            if(adj.size() > 1) {
                if (!finished.contains(i) && !visited[i]) {
                    solve(i);
                    if(done){
                        return;
                    }
                }
            }
            else{
                solve(i);
                if(done){
                    return;
                }
            }
        }
    }
    static void findDists(node st){
        Queue<node> q = new LinkedList<>();
        q.add(st);
        visited[st.ID] = true;
        while(!q.isEmpty()){
            node curr = q.poll();
            TreeSet<Integer> adj = adjList.get(curr.ID);
            for(int i: adj){
                if(!visited[i]){
                    q.add(new node(i, curr.distFrom1 + 1));
                    nodes.set(i, new node(i, curr.distFrom1 + 1));
                    visited[i] = true;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("clocktree.in");
        BufferedReader in = new BufferedReader(reader);
        File out = new File("clocktree.out");
        PrintWriter writer = new PrintWriter(out);
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i <= n; i++){
            adjList.add(new TreeSet<>());
            nodes.add(new node(i, 0));
        }
        times = new int[n + 1];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= n; i++){
            times[i] = Integer.parseInt(st.nextToken()) % 12;
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(in.readLine());
            int a, b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        in.close();
        int ans = 0;
        visited = new boolean[n + 1];
        findDists(nodes.get(1));
        List<Integer> evens = new ArrayList<>(), odds = new ArrayList<>();
        for(node i: nodes){
            if(i.distFrom1 % 2 == 1){
                odds.add(i.ID);
                continue;
            }
            evens.add(i.ID);
        }
        boolean[] processed = new boolean[n + 1];
        for(int i = 1; i <= n; i++){
            if(processed[i]){
                continue;
            }
            finished = new HashSet<>();
            clone = times.clone(); visited = new boolean[n + 1];
            clone[i]--;
            good = false; done = false;
            solve(i);
            if(good){
                int offSet = nodes.get(i).distFrom1 % 2;
                if(offSet == 0){
                    ans += evens.size() - 1;
                    for(int j : evens){
                        processed[j] = true;
                    }
                }
                else{
                    ans += odds.size();
                    for(int j : odds){
                        processed[j] = true;
                    }
                }
            }
        }
        writer.println(ans);
        writer.close();
    }
}

class node{
    int ID, distFrom1;
    public node(int ID, int distFrom1){
        this.ID = ID;
        this.distFrom1 = distFrom1;
    }
}