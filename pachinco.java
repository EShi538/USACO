import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int[][] matrix = new int[n][n];
    static int best = 0;
    static int[] dx = {1, 0};
    static int[] dy = {1, 1};
    static boolean[][] visited = new boolean[n][n];
    public static void rec(int x, int y, int ind, int sum){
        visited[y][x] = true;
        if(ind == n - 1){
            if(sum > best){
                best = sum;
            }
        }
        for(int i = 0; i < 2; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(inBound(nx, ny) && visited[ny][nx] == false && matrix[ny][nx] != -1){
                rec(nx, ny, ind + 1, sum + matrix[ny][nx]);
                visited[ny][nx] = false;
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
                matrix[i][j] = -1;
            }
        }
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                matrix[i - 1][j] = in.nextInt();
            }
        }
        rec(0, 0, 0, matrix[0][0]);
        System.out.println(best);
    }
}
