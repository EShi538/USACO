import java.io.*;
import java.util.*;
public class lazy {
    static int n, k;
    static int[] field = new int[1000001];
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("lazy.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int g = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
            field[x] = g;
        }
        in.close();
        int[] prefix = new int[1000001];
        for(int i = 0; i <= 1000000; i++){
            prefix[i] = (i == 0) ? field[i] : field[i] + prefix[i - 1];
        }
        int ans = 0;
        for(int i = k; i <= 1000000 - k; i++){
            int l = i - k, r = i + k;
            int sum = (l == 0) ? prefix[r] : prefix[r] - prefix[l - 1];
            ans = Math.max(ans, sum);
        }
        FileWriter out = new FileWriter("lazy.out");
        if(k > 1000000){
            ans = prefix[1000000];
        }
        out.write(Long.toString(ans));
        out.close();
    }
}
