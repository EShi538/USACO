import java.io.*;
public class moobuzz{
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("moobuzz.in"));
        PrintWriter out = new PrintWriter(new File("moobuzz.out"));
        n = Integer.parseInt(in.readLine());
        in.close();
        int ans = (int)solve();
        switch(ans % 15){
            case 3:
            case 0:
            case 12: {
                if(check(ans - 1) == n){
                    ans--;
                }
                else{
                    ans++;
                }
                break;
            }
            case 5:
            case 9: {
                if(check(ans - 1) == n){
                    ans--;
                }
                else{
                    ans += 2;
                }
                break;
            }
            case 6:
            case 10: {
                if(check(ans - 2) == n){
                    ans -= 2;
                }
                else{
                    ans++;
                }
                break;
            }
        }
        out.println(ans);
        out.close();
    }
    static long solve(){
        long l = 0, r = Integer.MAX_VALUE;
        while(l != r){
            long m =(int)((long)(l + r)/2);
            if(check(m) < n){
                l = m + 1;
            }
            else if(check(m) > n){
                r = m;
            }
            else{
                l = m; r = m;
            }
        }
        return l;
    }
    static long check(long m){
        long div3 = m/3, div5 = m/5, div15 = m/15;
        long t = (div3 + div5) - div15;
        return m - t;
    }
}