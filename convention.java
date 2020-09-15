import java.io.*;
import java.util.*;
public class convention {
    static int n, m, c;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("convention.in"));
        PrintWriter out = new PrintWriter(new File("convention.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        Arrays.sort(a);
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = Integer.MAX_VALUE, l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            int mid = (l + r)/2;
            if(valid(mid)){
                r = mid - 1;
                ans = Math.min(ans, mid);
            }
            else{
                l = mid + 1;
            }
        }
        return ans;
    }
    static boolean valid(int mid){
        int busCnt = 0;
        for(int i = 0; i < n; i++){
            int p = i;
            int max = a[i] + mid;
            int cnt = 0;
            while(p < n && a[p] <= max && cnt < c){
                p++;
                cnt++;
            }
            i = p - 1;
            busCnt++;
        }
        return busCnt <= m;
    }
}
