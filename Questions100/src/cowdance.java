import java.io.*;
import java.util.*;
public class cowdance {
    static int n, t;
    static int[] d;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        d = new int[n];
        for(int i = 0; i < n; i++){
            d[i] = Integer.parseInt(in.readLine());
        }       
        in.close();
        int l = 1, r = n + 1;
        int ans = Integer.MAX_VALUE;
        while(l <= r){
            int m = (l + r)/2;
            if(valid(m)){
                r = m - 1;
                ans = Math.min(ans, m);
            }
            else{
                l = m + 1;
            }
        }
        FileWriter out = new FileWriter("cowdance.out");
        out.write(Integer.toString(ans));
        out.close();
    }
    static boolean valid(int m){
        int timeTaken = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new sort3());
        for(int i = 0; i < m; i++){
            pq.add(d[i]);
        }
        for(int i = m; i < n; i++){
            timeTaken = pq.poll();
            pq.add(d[i] + timeTaken);
            if(timeTaken > t){
                return false;
            }
        }
        while(!pq.isEmpty()){
            timeTaken = pq.poll();
        }
        return timeTaken <= t;
    }
}
class sort3 implements Comparator<Integer>{
    public int compare(Integer a, Integer b){
        return a - b;
    }
}