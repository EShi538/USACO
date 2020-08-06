import java.io.*;
import java.util.*;
public class boatrace {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int[] weights = new int[n];
            Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
            for(int j = 0; j < n; j++){
                weights[j] = Integer.parseInt(st.nextToken());
                if(!map.containsKey(weights[j])){
                    map.put(weights[j], 1);
                }
                else{
                    map.put(weights[j], map.get(weights[j]) + 1);
                }
            }
            int ans = -1;
            for(int sum = 2; sum <= 100; sum++){
                int num = 0;
                boolean[] processed = new boolean[51];
                for(int key: map.keySet()){
                    if(map.containsKey(sum - key) && !processed[key] && !processed[sum - key]){
                        if(key == sum - key){
                            num += map.get(key)/2;
                            continue;
                        }
                        num += Math.min(map.get(key), map.get(sum - key));
                        processed[key] = true; processed[sum - key] = true;
                    }
                }
                ans = Math.max(num, ans);
            }
            System.out.println(ans);
        }
    }
}
