import java.io.*;
import java.util.*;
public class convenient {
    static int n, a, b;
    static int[] timeZones, PS;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        timeZones = new int[n];
        PS = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            timeZones[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        in.close();

        for(int i = 0; i < n; i++){
            PS[i] = (i == 0) ? timeZones[i] : PS[i - 1] + timeZones[i];
        }
        int ans = -1;
        for(int l = 0; l < n; l++){
            int r = l + (b - a) - 1;
            int sum;
            if(r >= n){
                r = r % n;
                sum = PS[n - 1] - PS[l - 1] + PS[r];
            }
            else{
                if(l == 0){
                    sum = PS[r];
                }
                else{
                    sum = PS[r] - PS[l - 1];
                }
            }
            ans = Math.max(ans, sum);
        }
        int ans1 = Integer.MAX_VALUE;
        for(int l = 0; l < n; l++){
            int r = l + (b - a) - 1;
            int sum;
            if(r >= n){
                r = r % n;
                sum = PS[n - 1] - PS[l - 1] + PS[r];
            }
            else{
                if(l == 0){
                    sum = PS[r];
                }
                else{
                    sum = PS[r] - PS[l - 1];
                }
            }
            if(sum == ans) {
                int check;
                if(a - l > 0){
                    check = a - l;
                }
                else{
                    check = n + (a - l);
                }
                ans1 = Math.min(ans1, check);
            }
        }
        System.out.print(ans1);
    }
}
