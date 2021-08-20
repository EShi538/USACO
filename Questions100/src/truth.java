import java.io.*;
import java.util.*;
public class truth {
    static int n, m;
    static List<ArrayList<connection3>> adjList = new ArrayList<>(), adjListRelevant= new ArrayList<>();
    static List<input> inputs = new ArrayList<>();
    static boolean[] visited;
    static int[] assignments;
    static boolean isGood = true, done = false;;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("truth.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            char type = st.nextToken().charAt(0);
            adjList.get(x).add(new connection3(y, type));
            inputs.add(new input(x, y, type));
        }
        in.close();
        int l = 1, r = m;
        int ans = 1;
        while(l <= r){
            int mid = (l + r)/2;
            adjListRelevant = new ArrayList<>();
            for(int i = 0; i <= n; i++){
                adjListRelevant.add(new ArrayList<>());
            }
            for(int i = 0; i < mid; i++){
                input currInput = inputs.get(i);
                adjListRelevant.get(currInput.x).add(new connection3(currInput.y, currInput.type));
            }
            visited = new boolean[n + 1];
            assignments = new int[n + 1];
            isGood = true; done = false;
            for(int i = 1; i <= n; i++){
                if(!visited[i] && !done && isGood){
                    checkValid(inputs.get(0).x, 1);
                }
            }
            if(isGood){
                l = m + 1;
                ans = Math.max(ans, mid);
            }
            else{
                r = m - 1;
            }
        }
        FileWriter out = new FileWriter("truth.out");
        out.write(Integer.toString(ans));
        out.close();
    }
    static void checkValid(int node, int num){
        visited[node] = true;
        assignments[node] = num;
        for(connection3 i: adjListRelevant.get(node)){
            if(!visited[i.node] && i.type == 'T'){
                checkValid(i.node, num);
                if(done){
                    return;
                }
            }
            else if(!visited[i.node] && i.type == 'L'){
                checkValid(i.node, -num);
                if(done){
                    return;
                }
            }
            else if(visited[i.node] && i.type == 'T'){
                isGood = num == assignments[i.node];
                if(!isGood){
                    done = true;
                    return;
                }
            }
            else{
                isGood = -num == assignments[i.node];
                if(!isGood){
                    done = true;
                    return;
                }
            }
        }
    }
}
class connection3{
    int node;
    char type;
    public connection3(int  node, char type){
        this.node = node;
        this.type = type;
    }
}
class input{
    int x, y;
    char type;
    public input(int x, int y, char type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}