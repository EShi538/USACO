import java.io.*;
import java.util.*;
public class holiday {
    static int m, n;
    static List<worker> workers = new ArrayList<>();
    static int[] big;
    static int[] bestBig;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(st.nextToken()), z = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            workers.add(new worker(t, z, y));
        }
        in.close();
        bestBig = new int[n];
        out.println(solve());
        int tot = m;
        for(int i = 0; i < n; i++){
            int blow = Math.min(tot, bestBig[i]);
            out.print(blow + " ");
            tot -= blow;
        }
        out.close();
    }
    static int solve(){
        int ans = Integer.MAX_VALUE, l = 0, r = Integer.MAX_VALUE;
        while(l <= r){
            big = new int[n];
            int mid = (l + r)/2;
            if(valid(mid)){
                ans = Math.min(ans, mid);
                bestBig = big.clone();
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        return ans;
    }
    static boolean valid(int mid){
        int balloons = 0;
        for(int i = 0; i < n; i++){
            int max = binSearch(i, mid);
            big[i] = max;
            balloons += max;
        }
        return balloons >= m;
    }
    static int binSearch(int i, int time){
        int ans = 0, l = 0, r = m;
        while(l <= r){
            int mid = (l + r)/2;
            if(valid1(mid, i, time)){
                ans = Math.max(mid, ans);
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }
        return ans;
    }
    static boolean valid1(int mid, int i, int time){
        int full = mid/workers.get(i).z;
        int remain = mid % workers.get(i).z;
        int tot = (remain == 0) ? full : full + 1;
        int breaktime = (tot - 1) * workers.get(i).y;
        int blowtime = mid * workers.get(i).t;
        return blowtime + breaktime <= time;
    }
}
class worker{
    int t, z, y;
    public worker(int t, int z, int y){
        this.t = t;
        this.z = z;
        this.y = y;
    }
}