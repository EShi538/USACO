import java.io.*;
import java.util.*;
public class ship {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(in.readLine());
        String forecast = in.readLine();
        in.close();

        int[] VPS = new int[n + 1];
        int[] HPS = new int[n + 1];
        for(int i = 1; i <= n; i++){
            switch(forecast.charAt(i - 1)){
                case 'R': HPS[i] = HPS[i - 1] + 1; break;
                case 'L': HPS[i] = HPS[i - 1] - 1; break;
                case 'D': VPS[i] = VPS[i - 1] - 1; break;
                case 'U': VPS[i] = VPS[i - 1] + 1; break;
            }
        }

        final int k = Math.abs(x2 - x) + Math.abs(y2 - y);
        long lb = 0;
        long ub = (long)1e18;
        long ans = Long.MAX_VALUE;
        while(lb != ub){
            long m = (lb + ub)/2;
            if(valid(n, m, x, y, x2, y2, forecast, VPS, HPS)){
                if(m < ans){
                    ans = m;
                }
                ub = m;
            }
            else{
                lb = m + 1;
            }
        }
        if(ans != Long.MAX_VALUE) {
            System.out.println(ans);
        }
        else{
            System.out.println("-1");
        }
    }
    static boolean valid(int n, long guess, int x, int y, int x2, int y2, String forecast, int[] VPS, int[]HPS){
        long completeCycleCnt = guess/n;
        long remainder = guess % n;
        long x3 = x + (completeCycleCnt * HPS[n]) + HPS[(int)remainder];
        long y3 = y + (completeCycleCnt * VPS[n]) + VPS[(int)remainder];
        return Math.abs(x2 - x3) + Math.abs(y2 - y3) <= guess;
    }
}

