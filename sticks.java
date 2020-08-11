import java.io.*;
import java.util.*;
public class sticks {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] sticks = new int[6];
        StringTokenizer st = new StringTokenizer(in.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 6; i++){
            sticks[i] = Integer.parseInt(st.nextToken());
            if(!map.containsKey(sticks[i])){
                map.put(sticks[i], 1);
            }
            else{
                map.put(sticks[i], map.get(sticks[i]) + 1);
            }
        }
        in.close();

        boolean foundLegs = false;
        for(int i: map.keySet()){
            if(map.get(i) > 3){
                map.put(i, map.get(i) - 4);
                foundLegs = true;
            }
            if(map.get(i) == 0){
                map.remove(i);
            }
            if(foundLegs){
                break;
            }
        }
        if(!foundLegs){
            System.out.println("Alien");
            return;
        }
        if(map.keySet().size() == 1){
            System.out.println("Elephant");
        }
        else if(map.keySet().size() == 2){
            System.out.println("Bear");
        }
        else{
            System.out.println("Alien");
        }
    }
}
