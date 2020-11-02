import java.io.*;
import java.util.*;
public class arrayRearrange {
    static int t;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = Integer.parseInt(in.readLine());
        for(int ts = 0; ts < t; ts++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
            int[] a = new int[n], b = new int[n];
            st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++){
                b[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a); Arrays.sort(b);
            if(valid(a, b, x)){
                out.println("Yes");
            }
            else{
                out.println("No");
            }
            in.readLine();
        }
        in.close();
        out.close();
    }
    static boolean valid(int[] a, int[] b, int x){
        for(int i = 0; i < a.length; i++){
            if(a[i] + b[b.length - i - 1] > x){
                return false;
            }
        }
        return true;
    }
}
