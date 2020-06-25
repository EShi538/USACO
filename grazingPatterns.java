import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static boolean[][] field = new boolean[5][5];
    static int cnt = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void DFS(int x, int y){
        System.out.println(x + " " + y);
        field[y][x] = false;
        if(eaten() && x == 4 && y == 4){
            cnt++;
            return;
        }
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(inBound(nx, ny) && field[ny][nx] == true){
                DFS(nx, ny);
                field[ny][nx] = true;
            }
        }
        return;
    }

    public static boolean eaten(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(field[i][j] == true){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean inBound(int x, int y){
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                field[i][j] = true;
            }
        }
        for(int i = 0; i < n; i++){
            int y = in.nextInt() - 1;
            int x = in.nextInt() - 1;
            field[y][x] = false;
        }
        DFS(0, 0);
        System.out.println(cnt);
    }
}
