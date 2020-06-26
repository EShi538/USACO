import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int[][] matrix = new int[n][n];
    static boolean[][] visited = new boolean[n][n];
    static int[] dx = {0, 1};
    static int[] dy = {-1, 0};
    static int best = Integer.MIN_VALUE;
    public static void DFS(int x, int y, int sum){
        visited[y][x] = true;
        if(x == n - 1 && y == 0){
            if(sum > best){
                best = sum;
            }
        }
        for(int i = 0; i < 2;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(inBound(nx, ny) && visited[ny][nx] == false){
                DFS(nx, ny, sum + matrix[ny][nx]);
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
                String x = in.next();
                StringBuilder input = new StringBuilder(x);
                input.delete(x.length() - 1,x.length());  
                String y = input.toString();
                switch(y){
                    case "T": matrix[i][j] = 10; break;
                    case "J": matrix[i][j] = 11; break;
                    case "Q": matrix[i][j] = 12; break;
                    case "K": matrix[i][j] = 13; break;
                    case "A": matrix[i][j] = 1; break;
                    default: matrix[i][j] = Integer.parseInt(y); break;
                }       
            }
        }
        DFS(0, n - 1, matrix[n - 1][0]);
        System.out.println(best);
    }
}
