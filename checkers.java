import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static char[][] board = new char[n][n];
    static List<coordinate> points = new ArrayList<coordinate>();
    static boolean[][] visited = new boolean[n][n];
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {-1, 1, 1, -1};
    static boolean finished = false;
    public static void DFS(int x, int y){
        if(finished == true){
            return;
        }
        if(done()){
            finished = true;
            return;
        }
        visited[y][x] = true;
        for(int i = 0; i < 4; i++){
            int nx1 = x + dx[i];
            int ny1 = y + dy[i];
            int nx = x + 2 * dx[i];
            int ny = y + 2 * dy[i];
            if(inBound(nx, ny) && visited[ny][nx] == false && board[ny][nx] == '+' && board[ny1][nx1] == 'o'){
                points.add(new coordinate(ny, nx));
                board[ny1][nx1] = 'x';
                DFS(nx, ny);
                if(finished == false){
                    points.remove(points.size() - 1);
                }
                board[ny1][nx1] = 'o';
                visited[ny][nx] = false;
            }
        }
        return;
    }

    public static boolean done(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'o'){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean inBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    public static void main(String[] args) throws Exception {
        int min = Integer.MAX_VALUE;
        boolean impossible = true;
        char[][] temp = new char[n][n];
        for(int i = 0; i < n; i++){
            String row = in.next();
            char[] chArr = row.toCharArray();
            board[i] = chArr;
            temp[i] = chArr;
        }
        List<coordinate> best = new ArrayList<coordinate>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'K'){
                    points.add(new coordinate(i, j));
                    DFS(j, i);
                    visited = new boolean[n][n];
                    if(finished == true){
                        finished = false;
                        if(points.size() < min){
                            min = points.size();
                            impossible = false;
                            best = new ArrayList<coordinate>(points);
                        }
                    }
                    points.clear();
                }
            }
        }
        if(impossible == false){
            for(coordinate i: best){
                System.out.println((i.y + 1) + " " + (i.x + 1));
            }
        }
        else{
            System.out.println("impossible");
        }
    }
}

class coordinate{
    int y;
    int x;
    public coordinate(int y, int x){
        this.y = y;
        this.x = x;
    }
}