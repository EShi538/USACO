import java.io.*;
import java.util.*;
public class paintbarn{
    static int n, k;
    static int[][] wall = new int[1005][1005];
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
            wall[y1][x1] = 1;
            wall[y2][x2 + 1] = -1;
            //wall[y2][x1] = 1;
            wall[y1][x2 + 1] = -1;
        }
        in.close();
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                System.out.print(wall[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        for(int i = 0; i < 1005; i++){
            for(int j = 0; j < 1005; j++){
                int sum = wall[i][j];
                if(i - 1 >= 0){
                    sum += wall[i - 1][j];
                }
                if(j - 1 >= 0){
                    sum += wall[i][j - 1];
                }
                if(i - 1 >= 0 && j - 1 >= 0){
                    sum -= wall[i - 1][j - 1];
                }
                wall[i][j] = sum;
            }
        }
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                System.out.print(wall[i][j] + " ");
            }
            System.out.println("");
        }
        int ans = 0;
        for(int i = 0; i < 1005; i++){
            for(int j = 0; j < 1005; j++){
                if(wall[i][j] == k){
                    ans++;
                }
            }
        }
        PrintWriter out = new PrintWriter(System.out);
        out.write(Integer.toString(ans));
        out.close();
    }
}