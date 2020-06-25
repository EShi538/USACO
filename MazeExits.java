import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int h = in.nextInt();
    static int w = in.nextInt();
    static int sty = in.nextInt() - 1;
    static int stx = in.nextInt() - 1;
    static int[][] maze = new int[h][w];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int exitCnt = 0;
    static point closestExit = new point(0, 0, 0);
    static int[][] maze1 = new int[h][w];

    public static void DFS(int x, int y){
        if(x == 0 || y == 0 || x == w - 1 || y == h - 1){
            exitCnt++;
        }
        maze[y][x] = 1;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(inBound(nx, ny) && maze[ny][nx] == 0){
                DFS(nx, ny);
            }
        }
        return;
    }

    public static void BFS(){
        Queue<point> q = new LinkedList<point>();
        q.add(new point(stx, sty, 0));
        maze1[sty][stx] = 1;
        while(!q.isEmpty()) {
            point curr = q.poll();
            if(curr.x == 0 || curr.y == 0 || curr.x == w - 1 || curr.y == h - 1){
                closestExit = new point(curr.x, curr.y, curr.depth);
                return;
            }
            for(int i = 0; i < 4; i++){
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(inBound(nx, ny) && maze1[ny][nx] == 0){
                    maze1[ny][nx] = 1;
                    q.add(new point(nx, ny, curr.depth + 1));
                }
            }
        }
        return;
    }
    public static boolean inBound(int x, int y){
        return x >= 0 && x < w && y >= 0 && y < h;
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < h; i++){
            String row = in.next();
            char[] chArr = row.toCharArray();
            int[] add = new int[w];
            for(int j = 0; j < w; j++){
               add[j] = Character.getNumericValue(chArr[j]);
            }
            maze[i] = add;
        }
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                maze1[i][j] = maze[i][j];
            }
        }
        DFS(stx, sty);
        BFS();
        System.out.println((closestExit.y + 1) + " " + (closestExit.x + 1));
        System.out.println(closestExit.depth);
        System.out.println(exitCnt);
    }
}

class point{
    int x;
    int y;
    int depth;
    public point(int x, int y, int depth){
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}