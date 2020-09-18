import java.io.*;
import java.util.*;
public class split {
    static int n;
    static List<point> points = new ArrayList<>();
    static TreeMap<Integer, Integer> l = new TreeMap<>(), r = new TreeMap<>();//y -> x
    static long maxX = -1, maxY = -1, minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("split.in"));
        PrintWriter out = new PrintWriter(new File("split.out"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            maxX = Math.max(maxX, x); maxY = Math.max(maxY, y);
            minX = Math.min(minX, x); minY = Math.min(minY, y);
            points.add(new point(x, y));
        }
        in.close();
        points.sort(new sort());
        long orig = (maxX - minX) * (maxY - minY);
        for(point i: points){
            if(r.containsKey(i.y)){
                r.put(i.y, r.get(i.y) + 1);
            }
            else{
                r.put(i.y, 1);
            }
        }
        long ans = orig;
        for(int index = 0; index < n; index++){
            point i = points.get(index);
            if(l.containsKey(i.y)){
                l.put(i.y, l.get(i.y) + 1);
            }
            else{
                l.put(i.y, 1);
            }
            r.put(i.y, r.get(i.y) - 1);
            if(r.get(i.y) == 0){
                r.remove(i.y);
            }
            long la = (long)(i.x - points.get(0).x) * (long)(l.lastKey() - l.firstKey());
            long ra = 0;
            if(index < n - 1) {
                ra = (long)(points.get(n - 1).x - points.get(index + 1).x) * (long)(r.lastKey() - r.firstKey());
            }
            ans = Math.min(ans, ra + la);
        }
        l = new TreeMap<>(); r = new TreeMap<>();
        points.sort(new sort1());
        for(point i: points){
            if(r.containsKey(i.x)){
                r.put(i.x, r.get(i.x) + 1);
            }
            else{
                r.put(i.x, 1);
            }
        }
        for(int index = 0; index < n; index++){
            point i = points.get(index);
            if(l.containsKey(i.x)){
                l.put(i.x, l.get(i.x) + 1);
            }
            else{
                l.put(i.x, 1);
            }
            r.put(i.x, r.get(i.x) - 1);
            if(r.get(i.x) == 0){
                r.remove(i.x);
            }
            long la = (long)(i.y - points.get(0).y) * (long)(l.lastKey() - l.firstKey());
            long ra = 0;
            if(index < n - 1){
                ra = (long)(points.get(n - 1).y - points.get(index + 1).y) * (long)(r.lastKey() - r.firstKey());
            }
            ans = Math.min(ans, ra + la);
        }
        out.println(orig - ans);
        out.close();
    }

}
class point{
    int x, y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class sort implements Comparator<point>{
    public int compare(point a, point b){
        return a.x - b.x;
    }
}
class sort1 implements Comparator<point>{
    public int compare(point a, point b){
        return a.y - b.y;
    }
}