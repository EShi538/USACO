import java.io.*;
import java.util.*;
public class magicship {
    static int x1, y1, x2, y2;
    static int n;
    static char[] a; 
    static int[] preU, preD, preL, preR;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(in.readLine());
        a = new char[n];
        String row = in.readLine();
        for(int i = 0; i < n; i++){
            a[i] = row.charAt(i);
        }
        in.close();
        preU = new int[n]; preD = new int[n]; preL = new int[n]; preR = new int[n];
        calcPrefixSums();
        long l = 1, r = Long.MAX_VALUE - 1;
        long ans = Long.MAX_VALUE;
        while(l <= r){
            long m = (l + r)/2;
            if(valid(m)){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        PrintWriter out = new PrintWriter(System.out);
        if(ans != Long.MAX_VALUE){
            out.write(Long.toString(ans));
        }
        else{
            out.write("-1");
        }
        out.close();
    }
    static void calcPrefixSums(){
        for(int i = 0; i < n; i++){
            switch(a[i]){
                case 'U': 
                    preU[i] = (i == 0) ? 1 : preU[i - 1] + 1;
                    preD[i] = (i == 0) ? 0 : preD[i - 1];
                    preL[i] = (i == 0) ? 0 : preL[i - 1];
                    preR[i] = (i == 0) ? 0 : preR[i - 1];
                    break;
                case 'D':
                    preD[i] = (i == 0) ? 1 : preD[i - 1] + 1;
                    preU[i] = (i == 0) ? 0 : preU[i - 1];
                    preL[i] = (i == 0) ? 0 : preL[i - 1];
                    preR[i] = (i == 0) ? 0 : preR[i - 1];
                    break;
                case 'L':
                    preL[i] = (i == 0) ? 1 : preL[i - 1] + 1;
                    preU[i] = (i == 0) ? 0 : preU[i - 1];
                    preD[i] = (i == 0) ? 0 : preD[i - 1];
                    preR[i] = (i == 0) ? 0 : preR[i - 1];
                    break;
                case 'R':
                    preR[i] = (i == 0) ? 1 : preR[i - 1] + 1;
                    preU[i] = (i == 0) ? 0 : preU[i - 1];
                    preD[i] = (i == 0) ? 0 : preD[i - 1];
                    preL[i] = (i == 0) ? 0 : preL[i - 1];
                    break;
            }
        }
    }
    static boolean valid(long m){
        long uCnt = (m % n == 0) ? (preU[n - 1] * (m/n)) : (preU[n - 1] * (m/n)) + (preU[(int)((m - 1) % n)]);
        long dCnt = (m % n == 0) ? (preD[n - 1] * (m/n)) : (preD[n - 1] * (m/n)) + (preD[(int)((m - 1) % n)]);
        long lCnt = (m % n == 0) ? (preL[n - 1] * (m/n)) : (preL[n - 1] * (m/n)) + (preL[(int)((m - 1) % n)]);
        long rCnt = (m % n == 0) ? (preR[n - 1] * (m/n)) : (preR[n - 1] * (m/n)) + (preR[(int)((m - 1) % n)]);
        long afterWeatherX = x1 - lCnt + rCnt, afterWeatherY = y1 - dCnt + uCnt;
        return Math.abs(afterWeatherX - x2) + Math.abs(afterWeatherY - y2) <= m;
    }
}
