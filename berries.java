import java.io.*;
import java.util.*;
public class berries {
    static int n, k;
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("berries.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        File out = new File("berries.out");
        PrintWriter writer = new PrintWriter(out);
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] trees = new int[n];
        st = new StringTokenizer(in.readLine());
        int max = 0;
        for(int i = 0; i < n; i++){
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }
        in.close();

        int ans = 0;
        for(int i = 1; i <= max; i++){
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int j : trees){
                int times = j / i;
                int remainder = j % i;
                if(!map.containsKey(i)){
                    map.put(i, times);
                }
                else{
                    map.put(i, map.get(i) + times);
                }
                if(remainder != 0) {
                    if (!map.containsKey(remainder)) {
                        map.put(remainder, 1);
                    } else {
                        map.put(remainder, map.get(remainder) + 1);
                    }
                }
            }
            int[] basket = new int[k];
            for(int j = 0; j < k; j++){
                if(map.isEmpty()){
                    break;
                }
                basket[j] = map.lastKey();
                map.put(map.lastKey(), map.get(map.lastKey()) - 1);
                if(map.get(map.lastKey()) == 0){
                    map.remove(map.lastKey());
                }
            }
            Arrays.sort(basket);
            int sum = 0;
            for(int j = 0; j < k/2; j++){
                sum += basket[j];
            }
            ans = Math.max(sum, ans);
        }
        writer.println(ans);
        writer.close();
    }
}
