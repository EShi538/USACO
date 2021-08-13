import java.io.*;
import java.util.*;
public class split {
    static int n;
    static List<pair2> cows = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("split.in"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            cows.add(new pair2(x, y));
        }
        in.close();
        long ans = -1;
        Collections.sort(cows, new sortX());
        long[] maxY = new long[n], minY = new long[n], minYRev = new long[n];
        for(int i = 0; i < n; i++){
            maxY[i] = (i == 0) ? (long)cows.get(0).y : Math.max(maxY[i - 1], (long)cows.get(i).y);
        }
        for(int i = 0; i < n; i++){
            minY[i] = (i == 0) ? (long)cows.get(0).y : Math.min(minY[i - 1], (long)cows.get(i).y);
        }
        for(int i = n - 1; i >= 0; i--){
            minYRev[i] = (i == n - 1) ? (long)cows.get(n - 1).y : Math.min(minYRev[i + 1], (long)cows.get(i).y);
        }
        long totArea = (long)(cows.get(n - 1).x - cows.get(0).x) * (maxY[n - 1] - minY[n - 1]);
        for(int i = 0; i < n - 1; i++){
            long areaLeft = (long)(cows.get(i).x - cows.get(0).x) * (maxY[i] - minY[i]);
            long areaRight = (long)(cows.get(n - 1).x - cows.get(i + 1).x) * (maxY[n - 1] - minYRev[i + 1]);
            ans = Math.max(ans, totArea - Math.abs(areaLeft + areaRight));
        }
        Collections.sort(cows, new sortY());
        long[] maxX = new long[n], minX = new long[n], minXRev = new long[n];
        for(int i = 0; i < n; i++){
            maxX[i] = (i == 0) ? (long)cows.get(0).x : Math.max(maxX[i - 1], (long)cows.get(i).x);
        }
        for(int i = 0; i < n; i++){
            minX[i] = (i == 0) ? (long)cows.get(0).x : Math.min(minX[i - 1], (long)cows.get(i).x);
        }
        for(int i = n - 1; i >= 0; i--){
            minXRev[i] = (i == n - 1) ? (long)cows.get(n - 1).x : Math.min(minXRev[i + 1], (long)cows.get(i).x);
        }
        for(int i = 0; i < n - 1; i++){
            long areaBottom = (maxX[i] - minX[i]) * (long)(cows.get(i).y - cows.get(0).y);
            long areaTop = (maxX[n - 1] - minXRev[i + 1]) * (long)(cows.get(n - 1).y - cows.get(i + 1).y);
            ans = Math.max(ans, totArea - Math.abs(areaBottom + areaTop));
        }
        FileWriter out = new FileWriter("split.out");
        out.write(Long.toString(ans));
        out.close();
    }
}
class pair2{
    int x, y;
    public pair2(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class sortX implements Comparator<pair2>{
    @Override
    public int compare(pair2 a, pair2 b){
        return a.x - b.x;
    }
}
class sortY implements Comparator<pair2>{
    @Override
    public int compare(pair2 a, pair2 b){
        return a.y - b.y;
    }
}