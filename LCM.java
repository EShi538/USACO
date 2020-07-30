import java.io.*;
import java.util.*;
public class LCM {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(2 * a > b){
                System.out.println("-1 -1");
            }
            else{
                System.out.println(a + " " + 2 * a);
            }
        }
        in.close();
    }
}
