import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
public class stroll {
    static int cnt = 0;
    static int stX = 0;
    static int stY = 0;
    public static void BFS(char[][] field, boolean[][] visited, int h, int w){
        int[] dx = {1, 1, 0};
        int[] dy = {0, 1, 1};
        Queue<p> q = new LinkedList<p>();
        q.add(new p(0, 0));
        visited[0][0] = true;
        while(!q.isEmpty()){
            p curr = q.poll();
            if(field[curr.y][curr.x] == 'B'){
                stX = curr.x;
                stY = curr.y;
                return;
            }
            for(int i = 0; i < 3; i++){
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(inBound(nx, ny, h, w) && visited[ny][nx] == false){
                    q.add(new p(nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }
        return;
    }

    public static boolean inBound(int x, int y, int h, int w){
        return x >= 0 && x < w && y >= 0 && y < h;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        char[][] field = new char[h][w];
        boolean[][] visited = new boolean[h][w];
        for(int i = 0; i < h; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < w; j++){
                field[i][j] = st.nextToken().charAt(0);
            }
        }  
        in.close();
        int[][] pascal = new int[h][w];
        BFS(field, visited, h, w);
        pascal[stY][stX] = 1;
        for(int i = stY; i < h; i++){
            for(int j = stX; j < w; j++){
                if(i == stY && j == stX){
                    continue;
                }
                //check above
                if(inBound(j, i - 1, h, w) && field[i - 1][j] != 'R'){
                    pascal[i][j] = pascal[i][j] + pascal[i - 1][j];
                }
                //check left
                if(inBound(j - 1, i, h, w) && field[i][j - 1] != 'R'){
                    pascal[i][j] = pascal[i][j] + pascal[i][j - 1];
                }
                if(field[i][j] == 'B'){
                    System.out.println(pascal[i][j]);
                    break;
                }
            }
        }
        System.out.println();
    }
}

class p{
    int x;
    int y;
    public p(int x, int y){
        this.x = x;
        this.y = y;
    }
}