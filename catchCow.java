import java.io.*;
import java.util.*;
public class catchCow {
    static int n, k;
    static Set<Long> visited = new HashSet<>();
    static long ans;
    static void bfs(){
        Queue<state> q = new LinkedList<>();
        q.add(new state(n, 0));
        visited.add((long)n);
        while(!q.isEmpty()){
            state curr = q.poll();
            if(curr.p == k){
                ans = curr.t;
                return;
            }
            if(!visited.contains((long)curr.p * 2)){
                q.add(new state((long)curr.p * 2, curr.t + 1));
                visited.add((long)curr.p * 2);
            }
            if(!visited.contains(curr.p + 1)){
                q.add(new state(curr.p + 1, curr.t + 1));
                visited.add(curr.p + 1);
            }
            if(!visited.contains(curr.p - 1)){
                q.add(new state(curr.p - 1, curr.t + 1));
                visited.add(curr.p - 1);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(n > k){
            out.println(n - k);
            out.close();
            return;
        }
        bfs();
        out.println(ans);
        out.close();
    }
}
class state{
    long p, t;
    public state(long p, long t){
        this.p = p;
        this.t = t;
    }
}