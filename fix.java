import java.io.*;
import java.util.*;
public class fix {
    static int cnt = 0;
    static boolean done = false;
    static void dfs(int x, int y, char[][] matrix, boolean[][] visited, int n, int m){
        visited[y][x] = true;
        if(matrix[y][x] == 'C'){
            done = true;
            return;
        }
        int nx = x, ny = y;
        if(matrix[y][x] == 'R') {
            nx++;
        }
        else{
            ny++;
        }
        if(inBound(nx, ny, n, m) && !visited[ny][nx]){
            dfs(nx, ny, matrix, visited, n, m);
        }
        else if(!inBound(nx, ny, n, m)) {
            cnt++;
            if (matrix[y][x] == 'R') {
                matrix[y][x] = 'D';
            } else {
                matrix[y][x] = 'R';
            }
            dfs(x, y, matrix, visited, n, m);
        }
    }
    static boolean inBound(int x, int y, int n, int m){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
            char[][] matrix = new char[n][m];
            boolean[][] visited = new boolean[n][m];
            for(int j = 0; j < n; j++){
                String row = in.readLine();
                matrix[j] = row.toCharArray();
            }
            for(int j = 0; j < n; j++){
                for(int e = 0; e < m; e++){
                    if(!visited[j][e]){
                        dfs(e, j, matrix, visited, n, m);
                    }
                }
            }
            System.out.println(cnt);
            cnt = 0;
        }
    }
}
