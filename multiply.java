import java.io.*;
import java.util.*;
public class multiply {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String num = in.readLine();
        int ans = 0;
        while(true){
            if(num.length() == 1){
                break;
            }
            long product = 1;
            for(int i = 0; i < num.length(); i++){
                product *= num.charAt(i) - 48;
            }
            num = Long.toString(product);
            ans++;
        }
        System.out.println(ans);
    }
}
