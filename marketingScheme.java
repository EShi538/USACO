import java.io.*;
import java.util.*;
public class marketingScheme {
    static int t;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = Integer.parseInt(in.readLine());
        for(int ts = 1; ts <= t; ts++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            if(l < (double)(r + 1)/2){
                out.println("NO");
            }
            else{
                out.println("YES");
            }
        }
        out.close();
        in.close();
    }
}
