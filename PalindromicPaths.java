import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static char[][] matrix = new char[n][n];
    static int[] dx1 = {0, 1};
    static int[] dy1 = {1, 0};
    static int[] dy = {-1, 0};
    static int[] dx = {0, -1};
    static Set<String> half1 = new TreeSet<String>();
    static Set<String> half2 = new TreeSet<String>();
    public static void DFS(point diag, point st, point en, String word){
        if(st.x == en.x && st.y == en.y){
            String w = word + matrix[diag.y][diag.x];
            half1.add(w);
            return;
        }
        for(int i = 0; i < 2; i++){
            int nx = st.x + dx[i];
            int ny = st.y + dy[i];
            if(inBound(nx, ny)){
                DFS(diag, new point(nx, ny), en, word + Character.toString(matrix[ny][nx]));
            }
        }
    }
    
    public static void DFS1 (point diag, point st, point en, String word){
        if(st.x == en.x && st.y == en.y){
            String w = word + matrix[diag.y][diag.x];
            if(half1.contains(w)){
                half2.add(w);
            }
            return;
        }
        for(int i = 0; i < 2; i++){
            int nx = st.x + dx1[i];
            int ny = st.y + dy1[i];
            if(inBound(nx, ny)){
                DFS1(diag, new point(nx, ny), en, word + Character.toString(matrix[ny][nx]));
            }
        }
    }

    public static boolean inBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < n; i++){
            String row = in.next();
            char[] chArr = row.toCharArray();
            matrix[i] = chArr;
        }
        for(int i = 0; i < n; i++){
            point curr = new point(i, (n - 1) - i);
            DFS(curr, curr, new point(0, 0), "");
            DFS1(curr, curr, new point(n - 1, n - 1), "");    
            half1.clear();          
        }
        System.out.println(half2.size());
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