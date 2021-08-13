import java.io.*;
import java.util.*;
public class div7 {
    static int n;
    static int[] a, prefix;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("div7.in"));
        n = Integer.parseInt(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        for(int i = 0; i < n; i++){
            int old = a[i];
            a[i] = old % 7;
        }
        prefix = new int[n];
        for(int i = 0; i < n; i++){
            prefix[i] = (i == 0) ? a[0] : prefix[i - 1] + a[i];
        }
        for(int i = 0; i < n; i++){
            int old = prefix[i];
            prefix[i] = old % 7;
        }
        int first = 0, last = 0;
        int ans = 0;
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < n; j++){
                if(prefix[j] == i){
                    first = j;
                    break;
                }
            }
            for(int j = n - 1; j >= 0; j--){
                if(prefix[j] == i){
                    last = j;
                    break;
                }
            }
            ans = Math.max(last - first, ans);
        }
        FileWriter out = new FileWriter("div7.out");
        out.write(Integer.toString(ans));
        out.close();
    }
}