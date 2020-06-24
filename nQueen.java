import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int cnt = 0;
    static int[][] board = new int[n][n];

    public static void DFS(int row){
        if(row == n){
            cnt++;
            return;
        }
        for(int i = 0; i < n; i++){
            if(board[row][i] == 0){
                occupy(i, row);
                DFS(row + 1);
                remove(i, row);
            }
        }
        return;
    }

    public static void main(String[] args) throws Exception {
        DFS(0);
        System.out.println(cnt);
    }

    public static void occupy(int x, int y){
        //occupy spot
        board[y][x]++;
        //occupy up
        for(int i = y - 1; i >= 0; i--){
            board[i][x]++;
        }
        //occupy down
        for(int i = y + 1; i < n; i++){
            board[i][x]++;
        }
        //occupy right
        for(int i = x + 1; i < n; i++){
            board[y][i]++;
        }
        //occupy left
        for(int i = x - 1; i >= 0; i--){
            board[y][i]++;
        }
        //occupy diagonals
        int px = x + 1; int py = y - 1;
        while(px < n && py >= 0){
            board[py][px]++;
            px++;
            py--;
        }
        px = x - 1; py = y - 1;
        while(px >= 0 && py >= 0){
            board[py][px]++;
            px--;
            py--;
        }
        px = x + 1; py = y + 1;
        while(px < n && py < n){
            board[py][px]++;
            px++;
            py++;
        }
        px = x - 1; py = y + 1;
        while(px >= 0 && py < n){
            board[py][px]++;
            px--;
            py++;
        }
        return;
    }

    public static void remove(int x, int y){
        //occupy spot
        board[y][x]--;
        //occupy up
        for(int i = y - 1; i >= 0; i--){
            board[i][x]--;
        }
        //occupy down
        for(int i = y + 1; i < n; i++){
            board[i][x]--;
        }
        //occupy right
        for(int i = x + 1; i < n; i++){
            board[y][i]--;
        }
        //occupy left
        for(int i = x - 1; i >= 0; i--){
            board[y][i]--;
        }
        //occupy diagonals
        int px = x + 1; int py = y - 1;
        while(px < n && py >= 0){
            board[py][px]--;
            px++;
            py--;
        }
        px = x - 1; py = y - 1;
        while(px >= 0 && py >= 0){
            board[py][px]--;
            px--;
            py--;
        }
        px = x + 1; py = y + 1;
        while(px < n && py < n){
            board[py][px]--;
            px++;
            py++;
        }
        px = x - 1; py = y + 1;
        while(px >= 0 && py < n){
            board[py][px]--;
            px--;
            py++;
        }
        return;
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
