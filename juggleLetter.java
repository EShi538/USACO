import java.io.*;
import java.util.*;
public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            String[] strings = new String[n];
            for(int j = 0; j < n; j++){
                strings[j] = in.readLine();
            }
            Map<Character, Integer> charCnt = new HashMap<Character, Integer>();
            for(String j: strings){
                for(int k = 0; k < j.length(); k++){
                    if(charCnt.containsKey(j.charAt(k))){
                        charCnt.put(j.charAt(k), charCnt.get(j.charAt(k)) + 1);
                    }
                    else{
                        charCnt.put(j.charAt(k), 1);
                    }
                }
            }
            boolean done = false;
            for(char j: charCnt.keySet()){
                if(charCnt.get(j) % n!= 0){
                    System.out.println("NO");
                    done = true;
                    break;
                }
            }
            if(done){
                continue;
            }
            System.out.println("YES");
        }
    }
}
