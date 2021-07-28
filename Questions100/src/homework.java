import java.io.*;
import java.util.*;
public class homework {
    static int n;
    static int[] k, prefix, lowest;
    static List<Integer> ans = new ArrayList<Integer>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("homework.in"));
        FileWriter out = new FileWriter("homework.out");
        n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        k = new int[n]; prefix = new int[n]; lowest = new int[n];
        for(int i = 0; i < n; i++){
            k[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        findPrefix();
        findLowest();
        int cnt = 2;
        double max = -1;
        for(int i = n - 2; i >= 1; i--){
            double ave = (double)((double)prefix[i] - (double)lowest[i])/(double)(cnt - 1);
            if(ave > max){
                max = ave;
            }
            cnt++;
        }
        cnt = 2;
        for(int i = n - 2; i >= 1; i--){
            double ave = (double)((double)prefix[i] - (double)lowest[i])/(double)(cnt - 1);
            if(ave == max){
                ans.add(n - cnt);
            }
            cnt++;
        }
        Collections.reverse(ans);
        for(int i: ans){
            out.write(Integer.toString(i) + "\n");
        }
        out.close();
    }
    static void findPrefix(){
        int sum = 0;
        for(int i = n - 1; i >= 0; i--){
            prefix[i] = sum + k[i];
            sum = prefix[i];
        }
    }
    static void findLowest(){
        int min = Integer.MAX_VALUE;
        for(int i = n - 1; i >= 0; i--){
            if(min > k[i]){
                min = k[i];
            }
            lowest[i] = min;
        }
    }
}
