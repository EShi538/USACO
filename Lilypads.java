import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int M = in.nextInt(); //number of rows
    static int N = in.nextInt(); //number of columns;
    static int M1 = in.nextInt(); //jump length in one direction
    static int M2 = in.nextInt(); //jump length in other direction
    static int[][] pond = new int[M][N];
    static point start = new point(0, 0, 0);
    static point dest = new point(0, 0, 0);
    static int size = 0;

    public static boolean inBound(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static int BFS(int[] Dx, int[] Dy) {
        Queue<point> q = new LinkedList<point>();
        q.add(start);
        pond[start.y][start.x] = 0;

        while(!q.isEmpty()){
            point curr = q.poll();
            for(int i = 0; i < Dx.length; i++){
                int nX = curr.x + Dx[i];
                int nY = curr.y + Dy[i];
                if(inBound(nX, nY) && pond[nY][nX] == 4){
                    return curr.depth + 1;
                }
                if(inBound(nX, nY) && pond[nY][nX] == 1){
                    pond[nY][nX] = 0;
                    q.add(new point(nX, nY, curr.depth + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                pond[i][j] = in.nextInt();
                if(pond[i][j] == 3){
                    start = new point(j, i, 0);
                }
                else if(pond[i][j] == 4){
                    dest = new point(j, i, -1);
                }
            }
        }  
        if(M1 == M2){
            int[]Dx = {M1, M1, -M1, -M1};
            int[]Dy = {M2, -M2, -M2, M2}; 
            System.out.print(BFS(Dx, Dy));
        }
        else if(M2 == 0 && M1 != 0) {
            int[]Dx = {0, 0, M1, -M1};
            int[]Dy = {M1, -M1, 0, 0};
            System.out.print(BFS(Dx, Dy));
        }
        else if(M1 == 0 && M2 != 0){
            int[]Dx = {0, 0, M2, -M2};
            int[]Dy = {M2, -M2, 0, 0};
            System.out.print(BFS(Dx, Dy));
        }
        else{
            int[]Dx = {M2, M1, M1, M2, -M2, -M1, -M1, -M2};
            int[]Dy = {M1, M2, -M2, -M1, -M1, -M2, M2, M1};
            System.out.print(BFS(Dx, Dy));
        }
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
