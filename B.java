import java.io.*;
import java.util.*;
public class B {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for(int j = 0; j < n; j++){
                a[j] = Integer.parseInt(st.nextToken());
            }
            int p = 1;
            for(int j = 0; j < n; j++){
                if(a[j] < 0){
                    continue;
                }
                p = Math.max(p, j + 1);
                while(p < n && a[p] >= 0){
                    p++;
                }
                if (p == n) {
                    break;
                }
                int c = a[j];
                a[j] = 0;
                a[p] = a[p] + c;
            }
            long sum = 0;
            for(int j: a){
                if(j > 0){
                    sum += j;
                }
            }
            out.println(sum);
        }
        out.close();
    }
}
