import java.io.*;
import java.util.*;
public class kick_start {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 1; tst <= t; tst++){
            String s = in.readLine();
            List<Boolean> lst = new ArrayList<>(); //T -> K, F -> S;
            int falseCnt = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == 'K' && i <= s.length() - 4){
                    if(s.startsWith("KICK", i)){
                        lst.add(true);
                    }
                }
                else if(s.charAt(i) == 'S' && i <= s.length() - 5){
                    if(s.startsWith("START", i)){
                        falseCnt++;
                        lst.add(false);
                    }
                }
            }
            int ans = 0;
            for(boolean i: lst){
                if (i){
                    ans += falseCnt;
                }
                else{
                    falseCnt--;
                }
            }
            out.println("Case #" + tst + ": " + ans);
        }
        in.close();
        out.close();
    }
}
