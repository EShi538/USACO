import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class cowdance {

    public static int search(int n, int k, int[] cows){
        int lb = 0;
        int ub = n;
        while(lb != ub){
            int m = (lb + ub)/2;
            if(good(m, k, n, cows)){
                 ub = m;
            }
            else{
                lb = m + 1;
            }
        }
        return lb;
    }

    public static boolean good(int x, int k, int n, int[] cows){
        Integer[] stage = new Integer[x];
        for(int i = 0; i < x; i++){
            stage[i] = cows[i];
        }
        int totTime = 0;
        int ind = x - 1;
        while(true){
            if(totTime > k){
                return false;
            }
            else if(done(stage)){
                break;
            }
            int min = Collections.min(Arrays.asList(stage));
            for(int j = 0; j < x; j++){
                if(stage[j] == Integer.MAX_VALUE){
                    continue;
                }
                stage[j] = stage[j] - min;
                if(stage[j] == 0){
                    if(ind + 1 < n){
                        stage[j] = cows[ind + 1];
                        ind++;
                    }
                    else{
                        stage[j] = Integer.MAX_VALUE;
                    }
                }
            }
            totTime = totTime + min;
        }
        return true;
    }
    public static boolean done(Integer[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != Integer.MAX_VALUE){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        FileReader file = new FileReader("cowdance.in");
        BufferedReader in = new BufferedReader(file);
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] cows = new int[n];
        for(int i = 0; i < n; i++){
            cows[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        File file1 = new File("cowdance.out");
        FileWriter writer = new FileWriter(file1);
        writer.write(Integer.toString(search(n, k, cows)));
        writer.close();
    }
}
