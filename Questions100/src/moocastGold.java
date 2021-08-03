import java.io.*;
import java.util.*;
public class moocastGold {
    static int n;
    static List<coordinate1> coordinates = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("moocastGold.in"));
        n = Integer.parseInt(in.readLine());
        coordinates.add(new coordinate1(-1, -1));
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            coordinates.add(new coordinate1(x,y));
        }
        in.close();
        long l = 0, r = Integer.MAX_VALUE;
        long ans = Integer.MAX_VALUE; 
        while(l <= r){
            long m = (l + r)/2;
            visited = new boolean[n + 1];
            valid(1, m);
            if(allVisited()){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        FileWriter out = new FileWriter("moocastGold.out");
        out.write(Long.toString(ans));
        out.close();
    }
    static void valid(int cow, long m){
        visited[cow] = true;
        for(int i = 1; i <= n; i++){
            if(i == cow){
                continue;
            }
            if(!visited[i] && calcDist(coordinates.get(cow).x, coordinates.get(cow).y, coordinates.get(i).x, coordinates.get(i).y) <= m){
                valid(i, m);
            }
        }
    }
    static int calcDist(int ax, int ay, int bx, int by){
        return (int)Math.pow((Math.abs(ax - bx)), 2) + (int)Math.pow((Math.abs(ay - by)), 2);
    }
    static boolean allVisited(){
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
}
class coordinate1{
    int x, y;
    public coordinate1(int x, int y){
        this.x = x;
        this.y = y;
    }
}