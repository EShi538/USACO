import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class berries {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("berries.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int[] trees = new int[n];
        int max = -1;
        for(int i = 0; i < n; i++){
            trees[i] = Integer.parseInt(st.nextToken());
            if(trees[i] > max){
                max = trees[i];
            }
        } 
        in.close();

        List<Integer> chunks = new ArrayList<Integer>();
        int ans = -1;
        for(int i = 1; i <= max; i++){
            for(int j = 0; j < trees.length; j++){
                for(int e = 0; e < trees[j]/i; e++){
                    chunks.add(i);
                }
                if(trees[j] % i != 0){
                    chunks.add(trees[j] % i);
                }
            }
            Collections.sort(chunks);
            int cnt = 0;
            int sum = 0;
            for(int j = chunks.size() - 1; j >= 0; j--){
                if(cnt == k){
                    break;
                }
                if(cnt >= k/2){
                    sum = sum + chunks.get(j);
                }
                cnt++;
            }
            if(sum > ans){
                ans = sum;
            }
            chunks.clear();
        }
        File out = new File("berries.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }

}