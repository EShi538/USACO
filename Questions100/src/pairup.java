import java.io.*;
import java.util.*;
public class pairup {
    static int n;
    static List<pair1> pairs = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            pairs.add(new pair1(x, y));
        }
        in.close();
        Collections.sort(pairs, new sort4());
        long ans = -1;
        int l = 0, r = n - 1;
        while(l < r){
            ans = Math.max(ans, pairs.get(l).y + pairs.get(r).y);
            if(pairs.get(l).x > pairs.get(r).x){
                int set = pairs.get(l).x - pairs.get(r).x;
                pairs.get(l).x = set;
                r--;
            }
            else if(pairs.get(r).x > pairs.get(l).x){
                int set = pairs.get(r).x - pairs.get(l).x;
                pairs.get(r).x = set;
                l++;
            }
            else{
                r--; l++;
            }
        }
        FileWriter out = new FileWriter("pairup.out");
        out.write(Long.toString(ans));
        out.close();
    }
}
class pair1{
    int x, y;
    public pair1(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class sort4 implements Comparator<pair1>{
    public int compare(pair1 a, pair1 b){
        return a.y - b.y;
    }
}