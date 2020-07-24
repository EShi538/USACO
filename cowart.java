import java.io.*;
import java.util.*;
public class cowart {
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static void check(int x, int y, int n, char[][] matrix, int orig){
        visited[y][x] = true;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(inBound(nx, ny, n) && !visited[ny][nx] && matrix[ny][nx] == orig){
                check(nx, ny, n, matrix, orig);
            }
        }
    }
    static boolean inBound(int x, int y, int n){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("cowart.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        char[][] matrixCow = new char[n][n];
        char[][] matrixPerson = new char[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            String row = in.readLine();
            for(int j = 0; j < n; j++){
                matrixPerson[i][j] = row.charAt(j);
                if(row.charAt(j) == 'G'){
                    matrixCow[i][j] = 'R';
                }
                else{
                    matrixCow[i][j] = row.charAt(j);
                }
            }
        }
        in.close();

        int cowCnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    cowCnt++;
                    check(j, i, n, matrixCow, matrixCow[i][j]);
                }
            }
        }
        int humanCnt = 0;
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    humanCnt++;
                    check(j, i, n, matrixPerson, matrixPerson[i][j]);
                }
            }
        }
        File out = new File("cowart.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(humanCnt) + " " + Integer.toString(cowCnt));
        writer.close();
    }
}
