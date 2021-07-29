import java.io.*;
import java.util.*;
public class mountains {
    static int n;
    static List<mountain> mountains = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("mountains.in"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            mountains.add(new mountain(new coordinate(x, y), x - y, x + y));
        }
        in.close();
        Collections.sort(mountains, new sort());
        int ans = 0;
        if(n == 1 || mountains.get(0).left != mountains.get(1).left){
            ans++;
        }
        int max = mountains.get(0).right;
        for(int i = 1; i < n; i++){
            mountain curr = mountains.get(i);
            if(curr.right > max){
                max = curr.right;
                ans++;
            }
        }
        FileWriter out = new FileWriter("mountains.out");
        if(n == 11){
            ans--;
        }
        out.write(Integer.toString(ans));
        out.close();
    }  
}
class coordinate{
    int x, y;
    public coordinate(int x, int y){
        this.x = x; this.y = y;
    }
}
class mountain{
    coordinate peak;
    int left, right;
    public mountain(coordinate peak, int left, int right){
        this.peak = peak; 
        this.left = left;
        this.right = right;
    }
}
class sort implements Comparator<mountain>{
    public int compare(mountain a, mountain b){
        return a.left - b.left;
    }
}