import java.io.*;
import java.util.*;
public class twoPlatforms {
    static int t;
    static int n, k;
    static int[] x;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = Integer.parseInt(in.readLine());
        for (int test = 0; test < t; test++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }
            int[] y = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                y[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(x);
            out.println(solve());
        }
        out.close();
    }
    static int solve(){
        int maxLeft = 0;
        int ans = 0;
        for(int i = 0; i < n; i++){
            int r = binSearch(x[i] + k, 1);
            int rLength;
            if(r < 0){
                if(r == -1) {
                    r = -(r + 1);
                }
                else if(r == -n - 1){
                    r = n - 1;
                }
                else{
                    r = -(r + 1) - 1;
                }
            }
            rLength = r - i + 1;
            int lLength = 0;
            if(i != 0) {
                int l = binSearch(x[i - 1] - k, -1);
                if(l < 0){
                    l = -(l + 1);
                }
                lLength = i - l;
            }
            maxLeft = Math.max(lLength, maxLeft);
            ans = Math.max(ans, maxLeft + rLength);
        }
        return ans;
    }
    static int binSearch(int target, int dir){
        int l = 0, r = n;
        int res = (dir == 1) ? 0 : Integer.MAX_VALUE;
        boolean found = false;
        while(l != r ){
            int m = (l + r)/2;
            if(x[m] < target){
                l = m + 1;
            }
            else if(x[m] > target){
                r = m;
            }
            else{
                found = true;
                res = (dir == 1) ? Math.max(m, res) : Math.min(m, res);
                if(dir == 1){
                    l = m + 1;
                }
                else{
                    r = m;
                }
                if(l < n && l == r && x[l] == target){
                    res = (dir == 1) ? Math.max(l, res) : Math.min(l, res);
                }
            }
        }
        if(found){
            return res;
        }
        else{
            return -(l + 1);
        }
    }
}