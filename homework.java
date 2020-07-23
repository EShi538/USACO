import java.util.*;
import java.io.*;
public class homework {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("homework.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] HW = new int[n + 1];
        for(int i = 1; i <= n; i++){
            HW[i] = Integer.parseInt(st.nextToken());
        }
        in.close();

        int[] HWPS = new int[n + 1];
        for(int i = 1; i <= n; i++){
            HWPS[i] = HWPS[i - 1] + HW[i];
        }
        int min = Math.min(HW[n], HW[n - 1]);
        float ans = -1;
        for(int i = n - 2; i >= 1; i--){
            int tot = HWPS[n] - HWPS[i];
            if((float)(tot - min)/(n - i - 1) > ans){
                ans = (float)(tot - min)/(n - i - 1);
            }
            if(HW[i] < min){
                min = HW[i];
            }
        }
        File out = new File("homework.out");
        FileWriter writer = new FileWriter(out);
        min = Math.min(HW[n], HW[n - 1]);
        List<Integer> ans1 = new ArrayList<Integer>();
        for(int i = n - 2; i >= 1; i--){
            int tot = HWPS[n] - HWPS[i];
            if((float)(tot - min)/(n - i - 1) == ans){
                ans1.add(i);
            }
            if(HW[i] < min){
                min = HW[i];
            }
        }
        Collections.sort(ans1);
        for(int i: ans1){
            writer.write(Integer.toString(i) + "\n");
        }
        writer.close();
    }
}
