import java.io.*;
import java.util.*;
import java.math.*;
public class meetingPlace {
    static int n;
    static int[] x, v;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        x = new int[n];
        v = new int[n];
        for(int i = 0; i < n; i++){
            x[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            v[i] = Integer.parseInt(st.nextToken());
        }
        int front = findInt();
        int decimal = findDecimal(front - 1);
        System.out.println(BigDecimal.valueOf(front - 1).add(BigDecimal.valueOf(decimal).divide(BigDecimal.valueOf(100000000), 8, RoundingMode.CEILING)));
        in.close();
    }
    static int findInt(){
        int ans = Integer.MAX_VALUE, l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            int m = (l + r)/2;
            if(check(BigDecimal.valueOf(m))){
                ans = Math.min(m, ans);
                r = m - 1;
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static int findDecimal(int front){
        int ans = Integer.MAX_VALUE, l = 0, r = 100000000;
        while(l <= r){
            int m = (r + l)/2;
            if(check(BigDecimal.valueOf(front).add(BigDecimal.valueOf(m).divide(BigDecimal.valueOf(100000000), 8, RoundingMode.CEILING)))){
                ans = Math.min(m, ans);
                r = m - 1;
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static boolean check(BigDecimal m){
        List<range> ranges = new ArrayList<>();
        for(int i = 0; i < n; i++){
            BigDecimal l  = BigDecimal.valueOf(x[i]).subtract(m.multiply(BigDecimal.valueOf(v[i])));
            BigDecimal r = BigDecimal.valueOf(x[i]).add(m.multiply(BigDecimal.valueOf(v[i])));
            ranges.add(new range(l, r));
        }
        BigDecimal min = BigDecimal.valueOf(Integer.MAX_VALUE), max = BigDecimal.valueOf(Integer.MIN_VALUE);
        for(range i: ranges){
            if(i.r.compareTo(min) == -1){
                min = i.r;
            }
            if(i.l.compareTo(max) == 1){
                max = i.l;
            }
        }
        return max.compareTo(min) <= 0;
    }
}

class range{
    BigDecimal l, r;
    public range(BigDecimal l, BigDecimal r){
        this.l = l;
        this.r = r;
    }
}