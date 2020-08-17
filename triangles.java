import java.io.*;
import java.util.*;
public class triangles {
    static int n;
    static int mod = (int)(1e9 + 7);
    static List<point> points = new ArrayList<>();
    static TreeMap<Integer, List<Integer>> mapX = new TreeMap<>(), mapY = new TreeMap<>(); //mapX: (x -> y's); mapY: (y -> x's)
    static TreeMap<Integer, List<Integer>> PSX = new TreeMap<>(), PSY = new TreeMap<>();
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("triangles.in");
        BufferedReader in = new BufferedReader(reader);
        File out = new File("triangles.out");
        PrintWriter writer = new PrintWriter(out);
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            points.add(new point(x, y));
        }
        in.close();

        for(point i: points){
            List<Integer> put;
            put = (mapX.containsKey(i.x)) ? mapX.get(i.x) : new ArrayList<>();
            put.add(i.y);
            mapX.put(i.x, put);
            List<Integer> put1;
            put1 = (mapY.containsKey(i.y)) ? mapY.get(i.y) : new ArrayList<>();
            put1.add(i.x);
            mapY.put(i.y, put1);
        }
        for(int i: mapX.keySet()){
            List<Integer> put = mapX.get(i);
            Collections.sort(put);
            mapX.put(i, put);
        }
        for(int i: mapY.keySet()){
            List<Integer> put = mapY.get(i);
            Collections.sort(put);
            mapY.put(i, put);
        }
        for(int i: mapX.keySet()){
            List<Integer> put = mapX.get(i);
            List<Integer> PS = new ArrayList<>();
            PS.add(put.get(0));
            for(int j = 1; j < put.size(); j++){
                PS.add(PS.get(j - 1) + put.get(j));
            }
            PSX.put(i, PS);
        }
        for(int i: mapY.keySet()){
            List<Integer> put = mapY.get(i);
            List<Integer> PS = new ArrayList<>();
            PS.add(put.get(0));
            for(int j = 1; j < put.size(); j++){
                PS.add(PS.get(j - 1) + put.get(j));
            }
            PSY.put(i, PS);
        }
        long ans = 0;
        for(point i: points){
            List<Integer> currX = mapX.get(i.x), currY = mapY.get(i.y);
            List<Integer> currPSX = PSX.get(i.x), currPSY = PSY.get(i.y);
            int posX = Collections.binarySearch(currY, i.x);
            int posY = Collections.binarySearch(currX, i.y);
            int XRsum = currPSY.get(currPSY.size() - 1) - currPSY.get(posX);
            int XLsum = (posX > 0) ? currPSY.get(posX - 1) : 0;
            if(posX > 0) {
                XLsum = currPSY.get(posX - 1);
            }
            int YRsum = currPSX.get(currPSX.size() - 1) - currPSX.get(posY);
            int YLsum = (posY > 0) ? currPSX.get(posY - 1) : 0;
            long xSum = (XRsum - i.x * (currY.size() - 1 - posX)) + Math.abs(XLsum - i.x * posX);
            long ySum = (YRsum - i.y * (currX.size() - 1 - posY)) + Math.abs(YLsum - i.y * posY);
            ans += (xSum % mod) * (ySum % mod);
        }
        writer.println(ans % mod);
        writer.close();
    }
}

class point{
    int x, y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}