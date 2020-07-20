import java.io.*;
import java.util.*;
public class proximity {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("proximity.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] cows = new int[n];
        Map<Integer, Integer> breeds = new HashMap<Integer, Integer>();
        for(int i = 0; i < n; i++){
            int breed = Integer.parseInt(in.readLine());
            cows[i] = breed;
            breeds.put(breed, 0);
        }
        in.close();
        int start = 0;
        int end = k;
        int ans = -1;
        List<Integer> currWindow = new ArrayList<Integer>();
        for(int i = 0; i <= k; i++){
            int cnt = breeds.get(cows[i]);
            cnt++;
            if(cnt > 1){
                if(cows[i] > ans){
                    ans = cows[i];
                }
            }
            breeds.put(cows[i], cnt);
        }

        for(int i = 1; i < n - k; i++){
            int cnt = breeds.get(cows[i - 1]);
            cnt--;
            breeds.put(cows[i - 1], cnt);
            cnt = breeds.get(cows[i + k]);
            cnt++;
            breeds.put(cows[i + k], cnt);
            if(breeds.get(cows[i + k]) > 1){
                if(cows[i + k] > ans){
                    ans = cows[i + k];
                }
            }
        }
        File out = new File("proximity.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}