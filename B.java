import java.io.*;
import java.util.*;
import java.math.*;
public class B {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 0; tst < t; tst++){
            long x = Long.parseLong(in.readLine());
            List<BigInteger> poss = new ArrayList<>();
            int l = 0;
            while(BigInteger.valueOf(2).pow(l + 1).subtract(BigInteger.ONE).compareTo(BigInteger.valueOf(x)) < 1){
                poss.add(BigInteger.valueOf(2).pow(l + 1).subtract(BigInteger.ONE));
                l++;
            }
            int ans = 0;
            BigInteger sum = BigInteger.ZERO;
            for(BigInteger i: poss){
                sum = sum.add((i.multiply(i.add(BigInteger.ONE))).divide(BigInteger.valueOf(2)));
                if(sum.compareTo(BigInteger.valueOf(x)) < 1){
                    ans++;
                }
            }
            out.println(ans);
        }
        out.close();
    }
}
