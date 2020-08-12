import java.io.*;
import java.util.*;
public class B {
    static int h, w, stX, stY;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        stX = Integer.parseInt(st.nextToken());
        stY = Integer.parseInt(st.nextToken());
        in.close();

        //first fill up first row
        //then fill up right side
        //fill up left side
        int y = stY - 1;
        while(y < h){
            y++;
            System.out.println(stX + " " + y);
        }
        y = stY;
        while(y >= 1){
            y--;
            if(y >= 1) {
                System.out.println(stX + " " + y);
            }
        }

        int cnt = 0;
        for(int i = stX - 1; i >= 1; i--){
            cnt++;
            if(cnt % 2 == 1){
                for(int j = 1; j <= h; j++){
                    System.out.println(i + " " + j);
                }
            }
            else{
                for(int j = h; j >= 1; j--){
                    System.out.println(i + " " + j);
                }
            }
        }
        for(int i = stX + 1; i <= w; i++){
            cnt++;
            if(cnt % 2 == 1){
                for(int j = 1; j <= h; j++){
                    System.out.println(i + " " + j);
                }
            }
            else{
                for(int j = h; j >= 1; j--){
                    System.out.println(i + " " + j);
                }
            }
        }

    }
}