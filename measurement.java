import java.io.*;
import java.util.*;
public class measurement {
    static int n, g;
    static Map<Integer, Integer> cows = new HashMap<Integer, Integer>();
    static List<record> records = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter out = new PrintWriter(new File("measurement.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        Set<Integer> IDs = new HashSet<Integer>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int day = Integer.parseInt(st.nextToken());
            int ID = Integer.parseInt(st.nextToken());
            cows.put(ID, g);
            StringBuilder str = new StringBuilder(st.nextToken());
            char front = str.charAt(0);
            int change = Integer.parseInt(str.deleteCharAt(0).toString());
            IDs.add(ID);
            if(front == '-'){
                change = -change;
            }
            records.add(new record(day, ID, change));
        }
        in.close();
        records.sort(new sort());
        int ans = 0;
        TreeMap<Integer, Set<Integer>> map = new TreeMap<>();
        map.put(g, new HashSet<Integer>());
        for(int i: IDs){
            map.get(g).add(i);
        }
        boolean first = true;
        for(record i: records){
            int before = cows.get(i.ID);
            cows.put(i.ID, cows.get(i.ID) + i.change);
            Set<Integer> prev = new HashSet<>(map.get(map.lastKey()));
            int test = cows.get(i.ID);
            if(!map.containsKey(test)){
                Set<Integer> s = new HashSet<Integer>();
                s.add(i.ID);
                map.put(cows.get(i.ID), s);
                map.get(before).remove(i.ID);
                if(map.get(before).size() == 0){
                    map.remove(before);
                }
            }
            else{
                Set<Integer> s = map.get(cows.get(i.ID));
                s.add(i.ID);
                map.put(cows.get(i.ID), s);
                map.get(before).remove(i.ID);
                if(map.get(before).size() == 0){
                    map.remove(before);
                }
            }
            if(!map.get(map.lastKey()).equals(prev) || first){
                ans++;
            }
            first = false;
        }
        out.println(ans);
        out.close();
    }
}
class record{
    int day, ID, change;
    public record(int day, int ID, int change){
        this.day = day;
        this.ID = ID;
        this.change = change;
    }
}
class sort implements Comparator<record>{
    public int compare(record a, record b){
        return a.day - b.day;
    }
}