import java.io.*;
import java.util.*;
public class trafficLights {
    static int x, n;
    public static void main(String[] args) throws Exception{
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new FileReader("in.in"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter(new File("out.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        x = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        TreeSet<Integer> lights = new TreeSet<>();
        TreeMap<Integer, Integer> gaps = new TreeMap<>();
        gaps.put(x, 1);
        lights.add(0); lights.add(x);
        int[] ls = new int[n];
        for(int i = 0; i < n; i++){
            ls[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < n; i++){
            int p = ls[i];
            lights.add(p);
            TreeSet<Integer> exclusiveHead = (TreeSet<Integer>) lights.headSet(p, false);
            TreeSet<Integer> exclusiveTail = (TreeSet<Integer>) lights.tailSet(p, false);
            int l = exclusiveHead.last();
            int r = exclusiveTail.first();
            int beforeGap = r - l;
            int A = p - l, B = r - p;
            gaps.put(beforeGap, gaps.get(beforeGap) - 1);
            if(gaps.get(beforeGap) == 0){
                gaps.remove(beforeGap);
            }
            if(gaps.containsKey(A)){
                gaps.put(A, gaps.get(A) + 1);
            }
            else{
                gaps.put(A, 1);
            }
            if(gaps.containsKey(B)){
                gaps.put(B, gaps.get(B) + 1);
            }
            else{
                gaps.put(B, 1);
            }
            out.print(gaps.lastKey() + " ");
        }
        out.close();
    }
}
