import java.io.*;
import java.util.*;
public class PhonomenalReviews {
    static int n, m;
    static Set<Integer> a = new HashSet<>();
    static Map<Integer, HashSet<Integer>> adjList = new HashMap<>();
    static List<node> nodes = new ArrayList<>();
    static boolean[] visited;
    static List<Integer> path = new ArrayList<>();
    static boolean done = false;
    static int cnt = 0;
    static void remove(int node){
        Iterator<Integer> it = adjList.get(node).iterator();
        int neighbor = it.next();
        removeNode(node, neighbor);
        if(adjList.get(neighbor).size() == 1 && !a.contains(neighbor)){
            remove(neighbor);
        }
    }
    static void removeNode(int remove, int parent){
        adjList.remove(remove);
        adjList.get(parent).remove(remove);
    }
    static void findDists(int stNode){
        Queue<node> q = new LinkedList<>();
        q.add(nodes.get(stNode));
        visited[stNode] = true;
        while(!q.isEmpty()){
            node curr = q.poll();
            for(int i: adjList.get(curr.n)){
                if(!visited[i]){
                    nodes.get(i).dist = nodes.get(curr.n).dist + 1;
                    q.add(nodes.get(i));
                    visited[i] = true;
                }
            }
        }
    }
    static void findPath(int node, int parent, int target){
        if(node == target){
            done = true;
            return;
        }
        for(int i: adjList.get(node)){
            if(i != parent){
                path.add(i);
                findPath(i, node, target);
                if(done){
                    return;
                }
                adjList.remove(adjList.size() - 1);
            }
        }
    }
    static void dfs(int node, int parent, Set<Integer> offLimit){
        for(int i: adjList.get(node)){
            if(i != parent && !offLimit.contains(i)){
                cnt++;
                dfs(i, node, offLimit);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        visited = new boolean[n];
        for(int i = 0; i < m; i++){
            a.add(Integer.parseInt(st.nextToken()));
        }
        for(int i = 0; i < n; i++){
            adjList.put(i, new HashSet<>());
            nodes.add(new node(i));
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        in.close();
        for(int i = 0; i < n; i++){
            if(adjList.get(i).size() == 1 && !a.contains(i)){
                remove(i);
            }
        }
        Iterator<Integer> it = adjList.keySet().iterator();
        findDists(it.next());
        node bestNode = new node(-1);
        int best = -1;
        for(node i: nodes){
            if(adjList.containsKey(i.n)) {
                if (i.dist > best) {
                    best = i.dist;
                    bestNode = i;
                }
            }
        }
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            nodes.add(new node(i));
        }
        findDists(bestNode.n);
        node bestOtherNode = new node(-1); best = -1;
        for(node i: nodes){
            if(adjList.containsKey(i.n)) {
                if (i.dist > best) {
                    best = i.dist;
                    bestOtherNode = i;
                }
            }
        }
        path.add(bestNode.n);
        findPath(bestNode.n, -1, bestOtherNode.n);
        int ans = path.size() - 1;
        for(int i = 0; i < path.size(); i++){
            if(i == 0){
                if(adjList.get(path.get(i)).size() > 1){
                    Set<Integer> offLimit = new HashSet<>();
                    offLimit.add(path.get(1));
                    cnt = 0;
                    dfs(path.get(i), -1, offLimit);
                    ans += 2 * cnt;
                }
            }
            else if(i == path.size() - 1){
                if(adjList.get(path.get(i)).size() > 1){
                    Set<Integer> offLimit = new HashSet<>();
                    offLimit.add(path.get(path.size() - 2));
                    cnt = 0;
                    dfs(path.get(i), -1, offLimit);
                    ans += 2 * cnt;
                }
            }
            else{
                if(adjList.get(path.get(i)).size() > 2){
                    Set<Integer> offLimit = new HashSet<>();
                    offLimit.add(path.get(i - 1));
                    offLimit.add(path.get(i + 1));
                    cnt = 0;
                    dfs(path.get(i), -1, offLimit);
                    ans += 2 * cnt;
                }
            }
        }
        out.println(ans);
        out.close();
    }
}
class node{
    int n, dist;
    public node(int n){
        this.n = n;
        this.dist = 0;
    }
}