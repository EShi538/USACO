import java.io.*;
public class revBinaryStringEnhanced {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int ts = 0; ts < t; ts++){
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            out.println(Math.min(solve(n, s, true), solve(n, s, false)));
        }
        in.close();
        out.close();
    }
    static int solve(int n, String s, boolean numt){
        boolean num = numt;
        int cnt = 0;
        for(int i = 0; i < n; i++){
            int p = i;
            if((num && s.charAt(i) == '0') || (!num && s.charAt(i) == '1')){
                cnt++;
                while(p < n && ((num && s.charAt(p) == '0') || (!num && s.charAt(p) == '1'))){
                    p++;
                    num = !num;
                }
                num = !num;
                i = p;
            }
            else{
                num = !num;
            }
        }
        return cnt;
    }

}
