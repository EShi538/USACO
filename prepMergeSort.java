import java.io.*;
import java.util.*;
public class prepMergeSort {
    static int n;
    static int[] a;
    static TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        for(int i = 0; i < n; i++){
            SortedMap<Integer, TreeSet<Integer>> tm = map.headMap(a[i]);
            if(tm.isEmpty()){
                TreeSet<Integer> s = new TreeSet<>();
                s.add(a[i]);
                map.put(a[i], s);
                continue;
            }
            TreeSet<Integer> s = map.get(tm.lastKey());
            s.add(a[i]);
            map.remove(tm.lastKey());
            map.put(s.last(), s);
        }
        List<Integer> s = new ArrayList<>(map.keySet());
        Collections.reverse(s);
        for(int i: s){
            for(int j: map.get(i)){
                out.print(j + " ");
            }
            out.println();
        }

        out.close();
    }
}