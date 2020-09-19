import java.io.*;
import java.util.*;
public class A {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 0; tst < t; tst++){
            int n = Integer.parseInt(in.readLine());
            String m = in.readLine();
            boolean rHasOdd = false;
            for(int i = 0; i < n; i += 2){
                if((m.charAt(i) - 48) % 2 == 1){
                    rHasOdd = true;
                    break;
                }
            }
            boolean bHasEven = false;
            for(int i = 1;i < n; i += 2){
                if((m.charAt(i) - 48) % 2 == 0){
                    bHasEven = true;
                    break;
                }
            }
            if(!rHasOdd){
                out.println(2);
            }
            else if(!bHasEven){
                out.println(1);
            }
            else{
                if(n % 2 == 0) {
                    out.println(2);
                }
                else{
                    out.println(1);
                }
            }
        }
        out.close();
    }
}
