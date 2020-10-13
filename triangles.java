import java.io.*;
import java.util.*;
public class triangles {
    static int n;
    static List<point> points = new ArrayList<>();
    static Map<Integer, List<Integer>> yOnX = new HashMap<>(), xOnY = new HashMap<>();
    static Map<Integer, List<Integer>> yOnXPS = new HashMap<>(), xOnYPS = new HashMap<>();
    static final int mod = (int)1e9 + 7;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter out = new PrintWriter(new File("triangles.out"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            points.add(new point(x, y));
        }
        in.close();
        for(point i: points){
            List<Integer> l = (!yOnX.containsKey(i.x)) ? new ArrayList<>() : yOnX.get(i.x);
            l.add(i.y);
            yOnX.put(i.x, l);
        }
        for(point i: points){
            List<Integer> l = (!xOnY.containsKey(i.y)) ? new ArrayList<>() : xOnY.get(i.y);
            l.add(i.x);
            xOnY.put(i.y, l);
        }
        for(int i: yOnX.keySet()){
            List<Integer> l = yOnX.get(i);
            Collections.sort(l);
            yOnX.put(i, l);
        }
        for(int i: xOnY.keySet()){
            List<Integer> l = xOnY.get(i);
            Collections.sort(l);
            xOnY.put(i, l);
        }
        for(int i: yOnX.keySet()){
            List<Integer> l = new ArrayList<>();
            for(int j = 0; j < yOnX.get(i).size(); j++){
                if(j == 0){
                    l.add(yOnX.get(i).get(0));
                }
                else{
                    l.add(yOnX.get(i).get(j) + l.get(j - 1));
                }
            }
            yOnXPS.put(i, l);
        }
        for(int i: xOnY.keySet()){
            List<Integer> l = new ArrayList<>();
            for(int j = 0; j < xOnY.get(i).size(); j++){
                if(j == 0){
                    l.add(xOnY.get(i).get(0));
                }
                else{
                    l.add(xOnY.get(i).get(j) + l.get(j - 1));
                }
            }
            xOnYPS.put(i, l);
        }
        long ans = 0;
        for(point i: points){
            List<Integer> xOnYPS1 = xOnYPS.get(i.y);
            List<Integer> yOnXPS1 = yOnXPS.get(i.x);
            int posX = Collections.binarySearch(xOnY.get(i.y), i.x);
            int posY = Collections.binarySearch(yOnX.get(i.x), i.y);
            long lx = (posX == 0) ? 0 : Math.abs(xOnYPS1.get(posX - 1) - (posX * i.x));
            long rx = Math.abs((xOnYPS1.get(xOnYPS1.size() - 1) - (xOnYPS1.get(posX))) - ((xOnYPS1.size() - posX - 1) * i.x));
            long ly = (posY == 0) ? 0 : Math.abs(yOnXPS1.get(posY - 1) - (posY * i.y));
            long ry = Math.abs((yOnXPS1.get(yOnXPS1.size() - 1) - (yOnXPS1.get(posY))) - ((yOnXPS1.size() - posY - 1) * i.y));
            ans += (((lx + rx) % mod) * ((ly + ry) % mod)) % mod;
        }
        out.println(ans % mod);
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