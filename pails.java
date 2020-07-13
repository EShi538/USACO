import java.util.*;
import java.io.*;
public class pails {
    static boolean[][] visited = new boolean [101][101];
    static int min = Integer.MAX_VALUE;
    static int m;
    public static void check(int x, int y, int xCap, int yCap, int k, int steps){
        visited[x][y] = true;
        if(steps == k){
            if(Math.abs(m - (x + y)) < min){
                min = Math.abs(m - (x + y));
            }
            return;
        }
        //pour
        int[] test = pour(x, y, xCap, yCap, 1);
        if(visited[test[0]][test[1]] == false){
            check(test[0], test[1], xCap, yCap, k, steps + 1);
            visited[test[0]][test[1]] = false;
        }
        test = pour(x, y, xCap, yCap, 2);
        if(visited[test[0]][test[1]] == false){
            check(test[0], test[1], xCap, yCap, k, steps + 1);
            visited[test[0]][test[1]] = false;
        }
        //empty
        if(visited[0][y] == false){
            check(0, y, xCap, yCap, k, steps + 1);
            visited[0][y] = false;
        }
        if(visited[x][0] == false){
            check(x, 0, xCap, yCap, k, steps + 1);
            visited[x][0] = false;
        }
        //fill
        if(visited[xCap][y] == false){
            check(xCap, y, xCap, yCap, k, steps + 1);
            visited[xCap][y] = false;
        }
        if(visited[x][yCap] == false){
            check(x, yCap, xCap, yCap, k, steps + 1);
            visited[x][yCap] = false;
        }
        return;
    }

    public static int[] pour(int x, int y, int xCap, int yCap, int pour){
        if(pour == 1){
            if(x < (yCap - y)){
                y = y + x;
                x = 0;
            }
            else if(x >= (yCap - y)){
                x = x - (yCap - y);
                y = yCap;
            }
        }
        else{
            if(y < (xCap - x)){
                x = x + y;
                y = 0;
            }
            else if(y >= (xCap - x)){
                y = y - (xCap - x);
                x = xCap;
            }
        }
        int[] ret = {x, y};
        return ret;
    }


    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("pails.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        in.close();

        for(int i = 1; i <= k; i++){
            visited = new boolean[101][101];
            check(0, 0, x, y, i, 0);
        }
        File out = new File("pails.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(min));
        writer.close();
    }
} 