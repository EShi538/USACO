import java.io.*;
//import java.util.*;
public class councils {
    static int n, k;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(in.readLine());
        n = Integer.parseInt(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        System.out.println(solve());
    }
    static long solve(){
        long ans = -1, l = 0, r = (long)1e15;
        while(l <= r){
            long m = (l + r)/2;
            if(valid(m)){
                ans = Math.max(ans, m);
                l = m + 1;
            }
            else{
                r = m - 1;
            }
        }
        return ans;
    }
    static boolean valid(long m){
        long sum = 0;
        for(int i: a){
            sum += Math.min(i, m);
        }
        long tot = m * (long)k;
        return sum >= tot;
    }
}
