import java.io.*;
import java.util.*;
public class socdist {
    static int n, m;
    static List<grass> fields = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter out = new PrintWriter(new File("socdist.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            long a = Long.parseLong(st.nextToken()), b = Long.parseLong(st.nextToken());
            fields.add(new grass(a, b));
        }
        in.close();
        fields.sort(new sort());
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = -1, l = 0, r = Integer.MAX_VALUE;
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
        long st = fields.get(0).l;
        long cnt = 0;
        for(grass i: fields){
            st = Math.max(i.l, st);
            long add = (st <= i.r) ? (i.r - st)/m + 1 : 0;
            cnt += add;
            st += m * add;
        }
        return cnt >= n;
    }
}
class grass{
    long l, r;
    public grass(long a, long b){
        this.l = a;
        this.r = b;
    }
}
class sort implements Comparator<grass>{
    public int compare(grass a, grass b){
        if(a.l < b.l){
            return -1;
        }
        else if(a.l > b.l){
            return 1;
        }
        return 0;
    }
}