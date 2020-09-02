import java.io.*;
import java.util.*;
public class artgallery2 {
    static int n, k;
    static int[] p, PS;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("artgallery2.in"));
        PrintWriter out = new PrintWriter(new File("artgallery2.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        p = new int[n];
        PS = new int[n];
        for(int i = 0; i < n; i++){
            p[i] = Integer.parseInt(st.nextToken());
            PS[i] = (i == 0) ? p[i] : PS[i - 1] + p[i];
        }
        int ans = (PS[n - 1] >= k) ? 1 : 0;
        for(int i = 1; i < n; i++){
            ans += (slide(i));
        }
        out.println(ans);
        out.close();
    }
    static int slide(int s){
        int l, r;
        int res = 0;
        for(int i = 0; i < n; i++){
            l = i;
            r = (i + s - 1) % n;
            int sum;
            if(l <= r){
                sum = (l == 0) ? PS[r] : PS[r] - PS[l - 1];
            }
            else{
                sum = PS[r] + (PS[n - 1] - PS[l - 1]);
            }
            if(sum >= k){
                res++;
            }
        }
        return res;
    }
}
