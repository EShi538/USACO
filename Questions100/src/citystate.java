import java.util.*;
import java.io.*;
public class citystate {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("citystate.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            String city = st.nextToken().substring(0, 2);
            String state = st.nextToken();
            String together = "";
            together = city + state;
            if(!map.containsKey(together)){
                map.put(together, 0);
            }
            map.put(together, map.get(together) + 1);
        }
        in.close();
        Set<String> words = map.keySet();
        int ans = 0;
        for(String i: words){
            String other = i.substring(2, 4) + i.substring(0, 2);
            if(map.containsKey(other) && !i.substring(2, 4).equals(i.substring(0, 2))){
                ans = ans + (map.get(i) * map.get(other));
            }
        }
        File out = new File("citystate.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans/2));
        writer.close();
    }
}
