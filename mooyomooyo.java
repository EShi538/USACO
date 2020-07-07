import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class mooyomooyo {
    static boolean remove = false;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int cnt = 0;
    public static void regionSize (char[][] board, int x, int y, boolean[][] visited, char orig, int n){
        visited[y][x] = true;
        cnt++;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(inBound(nx, ny, n) && board[ny][nx] == orig && visited[ny][nx] == false){
                regionSize(board, nx, ny, visited, orig, n);
            }
        }
        return;
    }

    public static void remove(char[][] board, int x, int y, boolean[][] visited, char orig, int n){
        board[y][x] = '0';
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(inBound(nx, ny, n) && board[ny][nx] == orig){
                remove(board, nx, ny, visited, orig, n);
            }
        }
        return;
    }


    public static boolean inBound(int x, int y, int n){
        return x >= 0 && x < 10 && y >= 0 && y < n;
    }
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("mooyomooyo.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char[][] board = new char[n][10];
        boolean[][] visited = new boolean[n][10];
        for(int i = 0; i < n; i++){
            board[i] = in.readLine().toCharArray();
        }
        in.close();

        while(true){
            boolean done = true;
            //remove everything
            for(int i = 0; i < n; i++){
                for(int j = 0; j < 10; j++){
                    if(board[i][j] != '0' && visited[i][j] == false){
                        regionSize(board, j, i, visited, board[i][j], n);
                        if(cnt >= k){
                            done = false;
                            remove(board, j, i, visited, board[i][j], n);
                        }
                        cnt = 0;
                    }
                }
            }
            visited = new boolean[n][10]; 
            if(done == true){
                break;
            }
            //gravity
            for(int i = 0; i < 10; i++){
                for(int j = n - 1; j >= 0; j--){
                    if(board[j][i] == '0'){
                        continue;
                    }
                    else{
                        int pos = j + 1;
                        while(pos < n && board[pos][i] == '0'){
                            pos++;
                        }
                        if(pos == j + 1){
                            continue;
                        }
                        else{
                            board[pos - 1][i] = board[j][i];
                            board[j][i] = '0';
                        }
                    }
                }
            }
        }
        File out = new File("mooyomooyo.out");
        FileWriter writer = new FileWriter(out);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 10; j++){
                writer.write(board[i][j]);
            }
            writer.write("\n");
        }
        writer.close();
    }
}