import java.io.*;
import java.util.*;
public class lemonade {
    static int n;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("lemonade.in"));
        PrintWriter out = new PrintWriter(new File("lemonade.out"));
        n = Integer.parseInt(in.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        Arrays.sort(a);
        for(int i = 0; i <= n/2; i++){
            int tmp = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = tmp;
        }
        int ans = 0;
        for(int i: a){
            if(i >= ans){
                ans++;
            }
        }
        out.println(ans);
        out.close();
    }
}
