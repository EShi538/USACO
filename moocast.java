import java.io.*;
import java.util.*;
public class moocast {
    static int n;
    static List<point> points = new ArrayList<>();
    static boolean[] visited;
    static boolean[] works;
    static boolean done;
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("moocast.in");
        BufferedReader in = new BufferedReader(reader);
        File out = new File("moocast.out");
        PrintWriter writer = new PrintWriter(out);
        n = Integer.parseInt(in.readLine());
        visited = new boolean[n];
        works = new boolean[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            points.add(new point(x, y));
        }
        in.close();
        writer.println(solve());
        writer.close();
    }
    static int solve(){
        int ans = Integer.MAX_VALUE, l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            int m = (l + r)/2;
            boolean valid = true;
            works = new boolean[n];
            for(int i = 0; i < n; i++){
                done = false;
                visited = new boolean[n];
                dfs(i, m);
                if(done){
                    works[i] = true;
                    continue;
                }
                if(!good(visited)){
                    valid = false;
                    break;
                }
                works[i] = true;
            }
            if(valid){
                ans = Math.min(ans, m);
                r = m - 1;
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static void dfs(int node, int m){
        visited[node] = true;
        for(int i = 0; i < n; i++){
            if(i == node){
                continue;
            }
            if(works[i]){
                done = true;
                return;
            }
            if(!visited[i] && dist(points.get(node).x, points.get(node).y, points.get(i).x, points.get(i).y) <= m){
                dfs(i, m);
                if(done){
                    return;
                }
            }
        }
    }
    static int dist(int x, int y, int x1, int y1){
        return (int)Math.pow(x - x1, 2) + (int)Math.pow(y - y1, 2);
    }
    static boolean good(boolean[] arr){
        for(boolean i: arr){
            if(!i){
                return false;
            }
        }
        return true;
    }
}
class point{
    int x, y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}