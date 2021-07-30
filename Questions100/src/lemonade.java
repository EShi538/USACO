import java.io.*;
import java.util.*;
public class lemonade{
    static int n;
    static int[] a;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("lemonade.in"));
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        Arrays.sort(a);
        for(int i = 0; i < a.length/2; i++){
            int tmp = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = tmp;
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(a[i] < i){
                break;
            }
            ans++;
        }
        FileWriter out = new FileWriter("lemonade.out");
        out.write(Integer.toString(ans));
        out.close();
    }
}