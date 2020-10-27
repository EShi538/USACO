import java.io.*;
public class revBinaryString {
    static int t;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = Integer.parseInt(in.readLine());
        for(int ts = 0; ts < t; ts++){
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            boolean[] a = new boolean[n];
            for(int i = 0; i < n; i++){
                if(s.charAt(i) == '1'){
                    a[i] = true;
                }
            }
            boolean[] tmp = new boolean[n];
            if (n >= 0) System.arraycopy(a, 0, tmp, 0, n);
            out.println(Math.min(solve(n, a, true), solve(n, tmp, false)));
        }
        in.close();
        out.close();
    }
    static int solve(int n, boolean[] a, boolean numt){
        int r = 0;
        boolean num = numt; //true -> 1, false -> 0
        int ans = 0;
        for(int i = 0; i < n; i++){
            r = Math.max(i, r);
            if(num && a[i]){
                num = false;
            }
            else if(!num && !a[i]){
                num = true;
            }
            else{
                ans++;
                if(a[i]){
                    while(r < n && !((r == n - 1 && !a[r]) || (r < n - 1 && !a[r] && !a[r + 1]))){
                        r++;
                    }
                    num = true;
                }
                else{
                    while(r < n && !((r == n - 1 && a[r]) || (r < n - 1 && a[r] && a[r + 1]))){
                        r++;
                    }
                    num = false;
                }
                if(r == n){
                    r--;
                }
                int cnt = 0;
                for(int j = i; j <= (i + r)/2; j++){
                    boolean tmp = a[j];
                    a[j] = a[r - cnt];
                    a[r - cnt] = tmp;
                    cnt++;
                }
            }
        }
        return ans;
    }
}
