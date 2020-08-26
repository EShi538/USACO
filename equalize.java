import java.io.*;
import java.util.*;
public class pairing {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(in.readLine());
        }
        int threshold = Integer.parseInt(in.readLine());
        int d = Integer.parseInt(in.readLine());
        in.close();
        List<ArrayList<Integer>> lst = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < n; i++){
            lst.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < n; i++){
            int num = arr[i];
            while(num != 0){
                lst.get(i).add(num);
                num /= d;
            }
            lst.get(i).add(0);
        }
        for(ArrayList<Integer> i: lst){
            System.out.println(i);
        }
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, List<Integer>> map1 = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < lst.get(i).size(); j++){
                List<Integer> l;
                if(!map.containsKey(lst.get(i).get(j))){
                    map.put(lst.get(i).get(j), 1);
                    l = new ArrayList<Integer>();
                }
                else{
                    map.put(lst.get(i).get(j), map.get(lst.get(i).get(j)) + 1);
                    l = map1.get(lst.get(i).get(j));
                }
                l.add(j);
                map1.put(lst.get(i).get(j), l);
            }
        }
        List<Integer> workingNums = new ArrayList<Integer>();
        for(int i: map.keySet()){
            if(map.get(i) >= threshold){
                workingNums.add(i);
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i: workingNums){
            List<Integer> l = map1.get(i);
            Collections.sort(l);
            int sum = 0;
            for(int j = 0; j < threshold; j++){
                sum += l.get(j);
            }
            ans = Math.min(ans, sum);
        }
        System.out.println(ans);
    }
}