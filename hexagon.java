import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class App {
    static Scanner in = new Scanner(System.in);
    static int k = in.nextInt(); //side length
    static int h = in.nextInt(); //start pasture
    static int l = in.nextInt(); //requested distance
    static int biggest = 2 * k - 1;
    static int[][] hex = new int[biggest][biggest];
    static point st = new point(0, 0, 0);
    static int area = ((k + biggest) * (biggest - k + 1)) - biggest;
    static boolean[] visited = new boolean[area + 1];
    static int[] dx = {0, 1, 1, 0, -1, -1};
    static int[] dy = {-1, 0, 1, 1, 0, -1};
    static List<Integer> good = new ArrayList<Integer>();
    public static void solve(){
        Queue<point> q = new LinkedList<point>();
        q.add(st);
        visited[hex[st.x][st.y]] = true;
        while(!q.isEmpty()){
            point curr = q.poll();
            if(curr.depth == l){
                good.add(hex[curr.x][curr.y]);
            }
            for(int i = 0; i < 6; i++){
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(inBound(nx, ny) && visited[hex[nx][ny]] == false && hex[nx][ny] != 0){
                    visited[hex[nx][ny]] = true;
                    q.add(new point(nx, ny, curr.depth + 1));
                }
            }
        }
        return;
    }

    public static boolean inBound(int x, int y){
        return x >= 0 && x < biggest && y >= 0 && y < biggest;
    }
    public static void main(String[] args) throws Exception {
        int ind = k;
        int col = 0;
        int cnt = 1;
        //fill in first side
        while(ind <= biggest){
            for(int i = ind - 1; i >= 0; i--){
                if(cnt == h){
                    st = new point(col, i, 0);
                }
                hex[col][i] = cnt;
                cnt++;
            }
            ind++;
            col++;
        }
        ind = biggest - 1;
        //fill in other side
        while(col < biggest){
            for(int i = biggest - 1; i >= biggest - ind; i--){
                if(cnt == h){
                    st = new point(col, i, 0);
                }
                hex[col][i] = cnt;
                cnt++;
            }
            ind--;
            col++;
        }
        solve();
        Collections.sort(good);
        System.out.println(good.size());
        for(int i: good){
            System.out.println(i);
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