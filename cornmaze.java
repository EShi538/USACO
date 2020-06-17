import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int H = in.nextInt();
    static int W = in.nextInt();
    static char[][] maze = new char[H][W];
    static Map<Character, ArrayList<point>> teleports = new HashMap<Character, ArrayList<point>>();
    static point st = new point(0, 0, 0);
    static point en = new point(0, 0, 0);
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] visited = new boolean[H][W];

    public static boolean inBound(int x, int y){
        return x >= 0 && x < W && y >= 0 && y < H;
    }

    public static int BFS (){
        Queue<point> q = new LinkedList<point>();
        q.add(st);
        visited[st.y][st.x] = true;
        while(!q.isEmpty()){
            point curr = q.poll();
            if(curr.x == en.x && curr.y == en.y){
                return curr.depth;
            }
            visited[curr.y][curr.x] = true;
            for(int i = 0; i < 4; i++){
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(nx == en.x && ny == en.y){
                    return curr.depth + 1;
                }
                if(inBound(nx, ny) && visited[ny][nx] == false && maze[ny][nx] == '.'){
                    q.add(new point(nx, ny, curr.depth + 1));
                    visited[ny][nx] = true;
                }
                else if(inBound(nx, ny) && maze[ny][nx] != '.' && maze[ny][nx] != '@' && maze[ny][nx] != '#' && maze[ny][nx] != '='){
                    ArrayList<point> stops = teleports.get(maze[ny][nx]);
                    if(stops.size() != 2){
                        continue;
                    }
                    if(stops.get(0).x == nx && stops.get(0).y == ny){
                        nx = stops.get(1).x;
                        ny = stops.get(1).y;
                    }
                    else if(stops.get(1).x == nx && stops.get(1).y == ny){
                        nx = stops.get(0).x;
                        ny = stops.get(0).y;
                    }
                    if(visited[ny][nx] == false){
                        q.add(new point(nx, ny, curr.depth + 1));
                        visited[ny][nx] = true;
                    }
                }
            } 
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        //input into matrix and map teleports
        for(int i = 0; i < H; i++){
            String row = in.next();
            char[] chArr = row.toCharArray();
            for(int j = 0; j < W; j++){
                if(chArr[j] != '.' && chArr[j] != '@' && chArr[j] != '#' && chArr[j] != '='){
                    if(!teleports.containsKey(chArr[j])){
                        ArrayList<point> value = new ArrayList<point>();
                        value.add(new point(j, i, 0));
                        teleports.put(chArr[j], value);
                    }
                    else{
                        ArrayList<point> value = teleports.get(chArr[j]);
                        value.add(new point(j, i, 0));
                        teleports.put(chArr[j], value);
                    }
                }
                else if(chArr[j] == '@'){
                    st = new point(j, i, 0);
                }
                else if(chArr[j] == '='){
                    en = new point(j, i, 0);
                }
            }
            maze[i] = chArr;
        }
        System.out.println(BFS());
        in.close();
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