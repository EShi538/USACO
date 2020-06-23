import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int b = in.nextInt();
    static List<boot> boots = new ArrayList<boot>();
    static int ans = Integer.MAX_VALUE;
    static int[] path = new int[n];
    static boolean[][] visited = new boolean[n][b]; //boot x pos
    public static void DFS(int pos, boot currBoot, int cnt, int currBootPos, int nextBootPos){
        visited[pos][currBootPos] = true;
        if(cnt >= ans){
            return;
        }
        if(pos == n - 1){
            //System.out.println("Answer");
            if(cnt < ans){
                ans = cnt;
            }
            //System.out.println(ans);
            return;
        }
        for(int i = 1; i <= currBoot.length; i++){
            int nx = pos + i;
            if(inBound(nx) && path[nx] <= currBoot.depth && visited[nx][currBootPos] == false){
                DFS(nx, currBoot, cnt, currBootPos, nextBootPos);
            }
        }
        for(int i = nextBootPos; i < b; i++){
            boot test = boots.get(i);
            if(test.depth >= path[pos] && visited[pos][i] == false){
                DFS(pos, test, i, i, i + 1);
            }
        }
        return;
    }

    public static boolean inBound(int x){
        return x >= 0 && x < n;
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < n; i++){
            path[i] = in.nextInt();
        }
        for(int i = 0; i < b; i++){
            boots.add(new boot(in.nextInt(), in.nextInt()));
        }
        DFS(0, new boot(0, 0), 0, 0, 0);
        System.out.println(ans);
    }
}

class boot{
    int depth;
    int length;
    public boot(int depth, int length){
        this.depth = depth;
        this.length = length;
    }
}