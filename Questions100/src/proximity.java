import java.io.*;
import java.util.*;
public class proximity{
    static int n, k;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("proximity.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        Set<Integer> activeCows = new HashSet<>();
        int ans = -1;
        for(int i = 0; i <= k; i++){
            if(activeCows.contains(a[i])){
                ans = Math.max(ans, a[i]);
                continue;
            }
            activeCows.add(a[i]);
        }
        for(int i = 1; i < n - k; i++){
            activeCows.remove(a[i - 1]);
            int curr = a[i + k];
            if(activeCows.contains(curr)){
                ans = Math.max(ans, curr);
            }
            activeCows.add(curr);
        }
        FileWriter out = new FileWriter("proximity.out");
        out.write(Integer.toString(ans));
        out.close();
    }
}