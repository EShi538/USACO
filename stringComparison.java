import java.io.*;
import java.util.*;
public class stringComparison {
    static String n, m;
    static TreeSet<Character> comp = new TreeSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = in.readLine();
        m = in.readLine();
        in.close();
        for(int i = 0; i < m.length(); i++){
            comp.add(m.charAt(i));
        }
        int ans = solve();
        if(ans != n.length() + 1){
            out.println(ans);
            out.close();
            return;
        }
        out.println("N/A");
        out.close();
    }
    static int solve(){
        int ans = n.length() + 1, l = 1, r = n.length();
        while(l <= r){
            int mid = (l + r)/2;
            if(valid(mid)){
                r = mid - 1;
                ans = Math.min(mid, ans);
            }
            else{
                l = mid + 1;
            }
        }
        return ans;
    }
    static boolean valid(int mid){
        int l, r;
        if(mid == n.length()){
            TreeSet<Character> ts = new TreeSet<>();
            for(int i = 0; i < n.length(); i++){
                ts.add(n.charAt(i));
            }
            return ts.containsAll(comp);
        }
        for(int i = 0; i < n.length() - mid; i++){
            l = i;
            r = i + mid - 1;
            TreeSet<Character> ts = new TreeSet<>();
            for(int j = l; j <= r; j++){
                ts.add(n.charAt(j));
            }
            if(ts.containsAll(comp)){
                return true;
            }
        }
        return false;
    }

}
