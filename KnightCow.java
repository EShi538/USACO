import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int W = in.nextInt();
    static int H = in.nextInt();
    static char[][] matrix = new char[H][W];
    static point st = new point(0, 0, 0);
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static int BFS(){
        Queue<point> q = new LinkedList<point>();
        q.add(st);
        matrix[st.y][st.x] = '*';
        while(!q.isEmpty()){
            point curr = q.poll();
            for(int i = 0; i < 8; i++){
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(inBound(nx, ny) && matrix[ny][nx] == 'H'){
                    return curr.depth + 1;
                }
                if(inBound(nx, ny) && matrix[ny][nx] != '*'){
                    q.add(new point(nx, ny, curr.depth + 1));
                    matrix[ny][nx] = '*';
                }
            }
        }
        return -1;
    }

    public static boolean inBound(int x, int y){
        return x >= 0 && x < W && y >= 0 && y < H;
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < H; i++){
            String row = in.next();
            char[] chArr = row.toCharArray();
            for(int j = 0; j < W; j++){
                if(chArr[j] == 'K'){
                    st = new point(j, i, 0);
                }
            }
            matrix[i] = chArr;
        }
        System.out.println(BFS());
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