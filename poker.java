import java.io.*;
import java.util.*;
public class poker {
    static int n;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("poker.in"));
        PrintWriter out = new PrintWriter(new File("poker.out"));
        n = Integer.parseInt(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        long ans = 0;
        do {
            for (int i = 0; i < n; i++) {
                if (a[i] == 0) {
                    continue;
                }
                int p = i;
                int min = Integer.MAX_VALUE;
                while (p < n && a[p] != 0) {
                    min = Math.min(a[p], min);
                    p++;
                }
                ans += min;
                for (int j = i; j < p; j++) {
                    a[j] -= min;
                }
                i = p - 1;
            }
        } while (!done());
        out.println(ans);
        out.close();
    }
    static boolean done(){
        for(int i: a){
            if(i != 0){
                return false;
            }
        }
        return true;
    }
}
