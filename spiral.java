import java.io.*;
import java.util.*;
public class spiral {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        in.close();

        int X = 0; int Y = 0;
        int ans = 0;
        int dir = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while(X != x || Y != y){
            if(X == 1 && Y == 0){
                ans++;
                dir = (dir + 1) % 4;
            }
            else if(X < 0 && Y < 0){
                if(-X == -Y){
                    ans++;
                    dir = (dir + 1) % 4;
                }
            }
            else if(X > 0 && Y > 0){
                if(X == Y){
                    ans++;
                    dir = (dir + 1) % 4;
                }
            }
            else if(X < 0 && Y > 0){
                if(-X == Y){
                    ans++;
                    dir = (dir + 1) % 4;
                }
            }
            else if(X > 0 && Y < 0){
                if(X == 1 - Y){
                    ans++;
                    dir = (dir + 1) % 4;
                }
            }
            X = X + dx[dir];
            Y = Y + dy[dir];
        }
        System.out.println(ans);
    }
}
