import java.io.*;
import java.util.*;
public class A {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int oCnt = 0;
            if(r % 2 == 1){
                oCnt++;
            }
            if(g % 2 == 1){
                oCnt++;
            }
            if(b % 2 == 1) {
                oCnt++;
            }
            if(w % 2 == 1){
                oCnt++;
            }
            if(oCnt == 1 || oCnt == 0) {
                System.out.println("YES");
                continue;
            }

            if(r != 0 && g != 0 && b != 0){
                r--; g--; b--; w += 3;
                oCnt = 0;
                if(r % 2 == 1){
                    oCnt++;
                }
                if(g % 2 == 1){
                    oCnt++;
                }
                if(b % 2 == 1) {
                    oCnt++;
                }
                if(w % 2 == 1){
                    oCnt++;
                }
                if(oCnt == 0 || oCnt == 1){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            }
            else{
                System.out.println("NO");
            }
        }
    }
}
