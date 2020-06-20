import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long b = in.nextLong();
        String start = "";
        for(int i = 0; i < n; i++){
            char ch = in.next().charAt(0);
            start = start + ch;
        }
        in.close();

        List<Integer> lightConfigs = new ArrayList<Integer>();
        Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
        int st = Integer.parseInt(start, 2);
        int x = -1;
        boolean first = true;
        x = st;
        int j = 0;

        while(first == true || !visited.containsKey(x)){
            visited.put(x, j);
            j++;
            lightConfigs.add(x);
            first = false;
            x = x ^ ((x >> 1) + ((x % 2) << (n - 1)));
        }
        int cycle = j - (visited.get(x));
        List<Integer> ansArr = new ArrayList<Integer>();
        for(int i = visited.get(x); i < visited.get(x) + cycle ; i++){
            ansArr.add(lightConfigs.get(i));
        }
        int mod = (int)(((b - visited.get(x)) % (cycle)));
        String ans = Integer.toBinaryString(ansArr.get(mod));
        if(ans.length() < n){
            int size = ans.length();
            for(int i = 0; i < n - size; i++){
                ans = "0" + ans;
            }
        }
        for(int i = 0; i < n; i++){
            System.out.println(ans.charAt(i));
        }
    }
}
