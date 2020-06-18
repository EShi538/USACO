import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    static Scanner in = new Scanner(System.in);
    static int[][] matrix = new int[5][5];
    static Set<Integer> nums = new HashSet<Integer>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void DFS (point st, int currNum, int depth){
        if(depth == 6){
            nums.add(currNum);
            return;
        }
        for(int i = 0; i < 4; i++){
            int nx = st.x + dx[i];
            int ny = st.y + dy[i];
            if(inBound(nx, ny)){
                DFS(new point(nx, ny), currNum + (int)(matrix[ny][nx] * Math.pow(10, depth)), depth + 1);
            }
        }
    }

    public static boolean inBound(int x, int y){
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                matrix[i][j] = in.nextInt();
            }
        }
        for(int i = 0; i < 5;i++){
            for(int j = 0; j < 5; j++){
                DFS(new point(j, i), 0, 0);
            }
        }
        System.out.print(nums.size());
    }
}

class point{
    int x;
    int y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}