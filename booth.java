import java.io.*;
import java.util.*;
public class booth {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if((((2 * a) - b)/3 >= 0 && (a - (2 * b))/3 <= 0) && ((2 * a) - b) % 3 == 0 && (a - (2 * b)) % 3 == 0){
                out.println("YES");
            }
            else{
                out.println("NO");
            }
        }
        out.close();
        in.close();
    }
}
