import java.io.*;
import java.util.*;
public class moocastSilver {
    static int n;
    static List<cow2> cows = new ArrayList<>();
    static boolean[] visited;
    static int cnt = 0;
    static void dfs(int cow){
        visited[cow] = true;
        cnt++;
        for(int i = 0; i < n; i++){
            if(i == cow){
                continue;
            }
            if(!visited[i] && works(cows.get(cow).x, cows.get(cow).y, cows.get(i).x, cows.get(i).y, cows.get(cow).p)){
                dfs(i);
            }
        }
    }
    static boolean works(int x1, int y1, int x2, int y2, int p){
        int dist = (int)Math.pow((x1 - x2), 2) + (int)Math.pow((y1 - y2), 2);
        return dist <= Math.pow(p, 2);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
            cows.add(new cow2(x, y, p, i));
        }
        in.close();
        int ans = 0;
        for(int i = 0; i < n; i++){
            visited = new boolean[n];
            cnt = 0;
            dfs(i);
            ans = Math.max(ans, cnt);
        }
        FileWriter out = new FileWriter("moocast.out");
        out.write(Integer.toString(ans));
        out.close();
    }
}
class cow2{
    int x, y, p, id;
    public cow2(int x, int y, int p, int id){
        this.x = x;
        this.y = y;
        this.p = p;
        this.id = id;
    }
}