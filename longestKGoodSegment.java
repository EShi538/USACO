import java.io.*;
import java.util.*;
public class longestKGoodSegment {
    static int n, k;
    static int[] a;
    static int[] ret;
    static int[] ansRet = new int[2];
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader in = new BufferedReader(new FileReader("in.in"));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        solve();
        out.println(ansRet[0] + " " + ansRet[1]);
        out.close();
    }
    static void solve(){
        int l = 1, r = n;
        while(l <= r){
            int m = (l + r)/2;
            ret = new int[2];
            if(valid(m)){
                l = m + 1;
                ansRet = ret.clone();
            }
            else{
                r = m - 1;
            }
        }
    }
    static boolean valid(int m){
        int l = 0, r = m - 1;
        Map<Integer, Integer> s = new HashMap<>();
        for(int i = l; i <= r; i++){
            if (s.containsKey(a[i])) {
                s.put(a[i], s.get(a[i]) + 1);
            }
            else{
                s.put(a[i], 1);
            }
        }
        if(s.size() <= k){
            ret[0] = l + 1;
            ret[1] = r + 1;
            return true;
        }
        for(int i = 1; i <= n - m; i++){
            l = i;
            r = i + m - 1;
            int remove = a[l - 1], add = a[r];
            s.put(remove, s.get(remove) - 1 );
            if(s.get(remove) == 0){
                s.remove(remove);
            }
            if(s.containsKey(add)){
                s.put(add, s.get(add) + 1);
            }
            else{
                s.put(add, 1);
            }
            if(s.size() <= k){
                ret[0] = l + 1;
                ret[1] = r + 1;
                return true;
            }
        }
        return false;
    }
}
