import java.io.*;
import java.util.*;
public class playlist {
    static int n;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        int p = 0;
        Set<Integer> s = new HashSet<>();
        int ans = -1;
        for(int i = 0; i < n; i++){
            p = Math.max(p, i);
            if(i > 0){
                s.remove(a[i - 1]);
            }
            while(p < n && !s.contains(a[p])){
                s.add(a[p]);
                p++;
            }
            ans = Math.max(ans, p - i);
        }
        return ans;
    }
}
