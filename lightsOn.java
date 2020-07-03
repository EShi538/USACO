import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
public class App {
    static Scanner in = new Scanner(System.in);
    static int N = in.nextInt();
    static int M = in.nextInt();
    static Map<LinkedList<Integer>, ArrayList<point>> map = new HashMap<LinkedList<Integer>, ArrayList<point>>();
    static boolean[][] matrix = new boolean[N + 1][N + 1]; //false -> Lights off; true -> Lights on
    static boolean[][] visited = new boolean[N + 1][N + 1];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void DFS(point st){
        LinkedList<Integer> coord = new LinkedList<Integer>();
        coord.add(st.x); coord.add(st.y);
        //set visited = true
        visited[st.y][st.x] = true;
        //light all lightable rooms
        ArrayList<point> lightUpPoints = map.get(coord); //null if no mapping
        if(lightUpPoints != null){
            for(point i: lightUpPoints){
                matrix[i.y][i.x] = true;
            }
        }
        //attempt to traverse to all neighbors
        for(int i = 0; i < 4; i++){
            int nx = st.x + dx[i];
            int ny = st.y + dy[i];
            if(inBound(nx, ny) && matrix[ny][nx] == true && visited[ny][nx] == false){
                DFS(new point(nx, ny));
            }
        }
        if(lightUpPoints != null){
            for(point i: lightUpPoints){
                int x = i.x;
                int y = i.y;
                if(visited[y][x] == false){
                    for(int j = 0; j < 4; j++){
                        int nx = x + dx[j];
                        int ny = y + dy[j];
                        if(inBound(nx, ny) && visited[ny][nx] == true && matrix[ny][nx] == true){
                            DFS(new point(nx, ny));
                        }
                    }
                }   
            }
        }
        return;
    }
    public static boolean inBound(int x, int y){
        return x >= 0 && x <= N && y >= 0 && y <= N;
    }
    public static void main(String[] args) throws Exception {
        matrix[1][1] = true;
        for(int i = 1; i <= M; i++){   
            point a = new point(in.nextInt(), in.nextInt());
            point b = new point(in.nextInt(), in.nextInt());
            LinkedList<Integer> coorda = new LinkedList<Integer>();
            LinkedList<Integer> coordb = new LinkedList<Integer>();
            coorda.add(a.x); coorda.add(a.y);
            coordb.add(b.x); coordb.add(b.y);

            if(!map.containsKey(coorda)){
                ArrayList<point> corr = new ArrayList<point>();
                corr.add(b);
                map.put(coorda, corr);
            }
            else{
                ArrayList<point> corr = map.get(coorda);
                corr.add(b);
                map.put(coorda, corr);
            }
        }
        DFS(new point(1, 1));
        int cnt = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(matrix[i][j] == true){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
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