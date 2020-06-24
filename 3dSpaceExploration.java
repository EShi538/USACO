import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static char[][][] space = new char[n][n][n];
    static int[] dx = {0, 1, 0, 0, -1, 0};
    static int[] dy = {-1, 0, 0, 1, 0, 0};
    static int[] dz = {0, 0, 1, 0, 0, -1};

    public static void floodfill(int x, int y, int z){
        Queue<point> q = new LinkedList<point>();
        q.add(new point(x, y, z));
        space[z][y][x] = '.';
        while(!q.isEmpty()){
            point curr = q.poll();
            for(int i = 0; i < 6; i++){
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                int nz = curr.z + dz[i];
                if(inBound(nx, ny, nz) && space[nz][ny][nx] == '*'){
                    q.add(new point(nx, ny, nz));   
                    space[nz][ny][nx] = '.';
                }
            }
        }
        return;
    }

    public static boolean inBound(int x, int y, int z){
        return x >= 0 && x < n && y >= 0 && y < n && z >= 0 && z < n;
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < n; i++){
            for(int e = 0; e < n; e++){
                String row = in.next();
                char[] chArr = row.toCharArray();
                space[i][e] = chArr;
            }   
        }

        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int e = 0; e < n; e++){
                    if(space[i][j][e] == '*'){
                        cnt++;
                        floodfill(e, j, i);
                    }
                }
            }
        }
        System.out.println(cnt);    
    }
}

class point{
    int x;
    int y;
    int z;
    public point(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}