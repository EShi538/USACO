import java.io.*;
import java.util.*;
public class pairup {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("pairup.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        Map<Integer, Integer> map = new TreeMap<>();
        List<Integer> keys = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int times = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            map.put(num, times);
            keys.add(num);
        }
        in.close();
        Collections.sort(keys);
        int p1 = 0;
        int p2 = map.size() - 1;
        int ans = -1;
        while(p1 <= p2){
            int sum = 0;
            if(map.get(keys.get(p1)) > map.get(keys.get(p2))){
                sum = keys.get(p1) + keys.get(p2);
                int prev = map.get(keys.get(p2));
                map.put(keys.get(p2), 0);
                map.put(keys.get(p1), map.get(keys.get(p1)) - prev);
                p2--;
            }
            else if(map.get(keys.get(p2)) > map.get(keys.get(p1))){
                sum = keys.get(p1) + keys.get(p2);
                int prev = map.get(keys.get(p1));
                map.put(keys.get(p1), 0);
                map.put(keys.get(p2), map.get(keys.get(p2)) - prev);
                p1++;
            }
            else{
                sum = keys.get(p1) + keys.get(p2);
                int prev = map.get(keys.get(p1));
                map.put(keys.get(p1), 0);
                map.put(keys.get(p2), 0);
                p1++;
                p2--;
            }
            System.out.print("P1: " + p1 + " P2: " + p2 + " SUM: " + sum);
            System.out.println();
            if(sum > ans){
                ans = sum;
            }
        }
        File out = new File("pairup.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}
