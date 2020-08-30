import java.io.*;
import java.util.*;
public class B {
    public static void main(String[] args) throws Exception {
        //BufferedReader in  = new BufferedReader(new FileReader("input.in"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        long min = 0;
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        Arrays.sort(arr);
        //min = (long)Math.pow(arr[n - 1], (double)((double)1/(double)(n - 1))) + 1;
        for(int i = 1; i < n; i++){
            min = Math.max(min, (long)Math.pow(arr[n - 1], (double)((double)1/(double)(n - 1))) + 1);
        }
        long ans = Long.MAX_VALUE;
        for(int i = 1; i <= min; i++){
            long sum = 0;
            for(int j = 0; j < n; j++){
                if(sum > ans){
                    break;
                }
                long num = (long)Math.pow(i, j);
                sum += (long)Math.abs((long)arr[j] - num);
            }
            ans = Math.min(sum, ans);
        }
        System.out.println(ans);
    }
}