import java.io.*;
import java.util.*;
public class stringGame {
    static String t, p;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = in.readLine();
        p = in.readLine();
        a = new int[t.length()];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < t.length(); i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = 0, l = 0, r = t.length();
        while(l <= r){
            int m = (l + r)/2;
            if(valid(m)){
                l = m + 1;
                ans = Math.max(m, ans);
            }
            else{
                r = m - 1;
            }
        }
        return ans;
    }
    static boolean valid(int m){
        char[] str = t.toCharArray();
        for(int i = 0; i < m; i++){
            str[a[i] - 1] = '.';
        }
        StringBuilder sb = new StringBuilder("");
        for(char i: str){
            if(i != '.'){
                sb.append(i);
            }
        }
        String remain = sb.toString();
        int index = 0;
        for(int i = 0; i < remain.length(); i++){
            if(remain.charAt(i) == p.charAt(index) && index == p.length() - 1){
                return true;
            }
            if(remain.charAt(i) == p.charAt(index)){
                index++;
            }
        }
        return false;
    }
}
