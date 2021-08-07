import java.io.*;
import java.util.*;
public class stacking {
    static int n, k;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("stacking.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n + 1];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            a[l]++;
            if(r < n){
                a[r + 1]--;
            }
        }
        in.close();
        for(int i = 1; i <= n; i++){
            a[i] = a[i - 1] + a[i];
        }
        Arrays.sort(a);
        FileWriter out = new FileWriter("stacking.out");
        out.write(Integer.toString(a[(n + 1)/2]));
        out.close();
    }
}
