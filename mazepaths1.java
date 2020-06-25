import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int[][] matrix = new int[n][n];
    static int cnt = 0;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void DFS(int x, int y){
        matrix[y][x] = 1;
        if(x == n - 1 && y == n - 1){
            cnt++;
            return;
        }
        for(int i = 0; i < 2; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(inBound(nx, ny) && matrix[ny][nx] == 0){
                DFS(nx, ny);
                matrix[ny][nx] = 0;
            }
        }
        return;
    }

    public static boolean inBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = in.nextInt(); //0 = land; 1 = water
            }
        }
        DFS(0, 0);
        System.out.println(cnt);
    }
}
