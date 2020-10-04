import java.io.*;
import java.util.*;
public class horizon {
    static int n;
    static List<line> lines = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
            lines.add(new line(a, i, h, true));
            lines.add(new line(b, i, h, false));
        }
        in.close();
        lines.sort(new sort());
        TreeMap<Integer, Integer> m = new TreeMap<>();
        long ans = 0;
        for(int i = 0; i < 2 * n; i++){
            if(i != 0 && !m.isEmpty()) {
                ans += (long)m.lastKey() * (long)(lines.get(i).x - lines.get(i - 1).x);
            }
            if(lines.get(i).t){
                if(m.containsKey(lines.get(i).h)){
                    m.put(lines.get(i).h, m.get(lines.get(i).h) + 1);
                }
                else{
                    m.put(lines.get(i).h, 1);
                }
            }
            else{
                m.put(lines.get(i).h, m.get(lines.get(i).h) - 1);
                if(m.get(lines.get(i).h) == 0){
                    m.remove(lines.get(i).h);
                }
            }
        }
        out.println(ans);
        out.close();
    }
}
class line{
    int x, b, h;
    boolean t;
    public line(int x, int b, int h, boolean t){
        this.x = x;
        this.b = b;
        this.h = h;
        this.t = t;
    }
}
class sort implements Comparator<line>{
    public int compare(line a, line b){
        return a.x - b.x;
    }
}