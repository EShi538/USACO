import java.io.*;
import java.util.*;
public class hamburgers {
    static String burger;
    static long n_b, n_s, n_c, p_b, p_s, p_c, b_b, b_s, b_c;
    static long r;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        burger = in.readLine();
        StringTokenizer st = new StringTokenizer(in.readLine());
        n_b = Long.parseLong(st.nextToken());
        n_s = Long.parseLong(st.nextToken());
        n_c = Long.parseLong(st.nextToken());
        st = new StringTokenizer(in.readLine());
        p_b = Long.parseLong(st.nextToken());
        p_s = Long.parseLong(st.nextToken());
        p_c = Long.parseLong(st.nextToken());
        r = Long.parseLong(in.readLine());
        in.close();
        for(int i = 0; i < burger.length(); i++){
            if(burger.charAt(i) == 'B'){
                b_b++;
            }
            else if(burger.charAt(i) == 'S'){
                b_s++;
            }
            else{
                b_c++;
            }
        }
        System.out.println(solve());
    }
    static long solve(){
        long ans = 0, l = 0, r = (long)1e15;
        while(l <= r){
            long m = (l + r)/2;
            if(check(m)){
                ans = Math.max(ans, m);
                l = m + 1;
            }
            else{
                r = m - 1;
            }
        }
        return ans;
    }
    static boolean check(long m){
        long b = Math.max(0, (b_b * m) - n_b);
        long s = Math.max(0, (b_s * m) - n_s);
        long c = Math.max(0, (b_c * m) - n_c);
        long test = (long)(b * p_b) + (long)(s * p_s) + (long)(c * p_c);
        return  test <= r;
    }
}
