import java.io.*;
import java.util.*;
public class candybox {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(in.readLine());
        for(int i = 0; i < q; i++){
            int n = Integer.parseInt(in.readLine());
            int[] candies = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                candies[j] = Integer.parseInt(st.nextToken());
            }
            Map<Integer, Integer> map = new TreeMap<>();
            for(int j: candies){
                if(!map.containsKey(j)){
                    map.put(j, 1);
                }
                else{
                    map.put(j, map.get(j) + 1);
                }
            }
            Set<Integer> already = new HashSet<>();
            int ans = 0;
            for(int key: map.keySet()){
                int max = -1;
                for(int j = 1; j <= map.get(key); j++){
                    if(already.contains(j)){
                        continue;
                    }
                    max = Math.max(max, j);
                }
                if(max != -1){
                    already.add(max);
                    ans += max;
                }
            }
            System.out.println(ans);
        }
    }
}
