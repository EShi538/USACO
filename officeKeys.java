import java.io.*;
import java.util.*;
public class officeKeys {
    static int n, k, p;
    static int[] a, b;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader in = new BufferedReader(new FileReader("in.in"));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        a = new int[n];
        b = new int[k];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < k; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static long solve(){
        long ans = Integer.MAX_VALUE, l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            long m = (l + r)/2;
            if(valid(m)){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static boolean valid(long m){
        Set<Integer> s = new HashSet<>();
        Set<Integer> s1 = new HashSet<>();
        for(int i = 0; i < k; i++){
            for(int j = 0; j < n; j++){
                if(Math.abs(b[i] - a[j]) + Math.abs(b[i] - p) <= m){
                    s.add(j);
                    s1.add(i);
                }
            }
        }
        return s.size() == n && s1.size() >= n;
    }
}