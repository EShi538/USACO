import java.io.*;
import java.util.*;
public class towers {
    static int n;
    static int[] a;
    static TreeMap<Integer, Integer> map = new TreeMap<>();
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
        int ans = 0;
        for(int i: a){
            Integer cKey = map.ceilingKey(i + 1);
            if(cKey == null){
                ans++;
            }
            else{
                map.put(cKey, map.get(cKey) - 1);
                if(map.get(cKey) == 0){
                    map.remove(cKey);
                }
            }
            if(!map.containsKey(i)) {
                map.put(i, 1);
            }
            else{
                map.put(i, map.get(i) + 1);
            }
        }
        out.println(ans);
        out.close();
    }
}