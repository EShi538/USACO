import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class BinarySubstring {
    static String n;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = in.readLine();
        in.close();
        BigInteger cnt = BigInteger.ZERO;
        for(int i = 0 ; i < n.length(); i++){
            if(n.charAt(i) == '0'){
                cnt = cnt.add(BigInteger.ONE);
            }
        }
        out.println((cnt.multiply(cnt.subtract(BigInteger.ONE))).divide(BigInteger.valueOf(2)));
        out.close();
    }
}
