import java.io.*;
import java.util.*;
public class moop {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("moop.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        List<point> points = new ArrayList<point>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            points.add(new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i + 1));
        }
        in.close();
        Collections.sort(points);
        int[] max = new int[n];
        max[n - 1] = points.get(n - 1).y;
        for(int i = n - 2; i >= 0; i--){
            max[i] = Math.max(points.get(i).y, max[i + 1]);
        }
        int[] min = new int[n];
        min[0] = points.get(0).y;
        for(int i = 1; i < n; i++){
            min[i] = Math.min(min[i - 1], points.get(i).y);
        }
        int ans = 1;
        for(int i = 0; i < n - 1; i++){
            if(min[i] > max[i + 1]){
                ans++;
            }
        }
        File out = new File("moop.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}

class point implements Comparable<point>{
    int ID;
    int x;
    int y;
    public point(int x, int y, int ID){
        this.x = x;
        this.y = y;
        this.ID = ID;
    }
    @Override
    public int compareTo(point other){
        if(this.x == other.x){
            return this.y - other.y;
        }
        return this.x - other.x;
    }
}