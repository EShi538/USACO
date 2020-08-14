import java.io.*;
import java.util.*;
public class maxSum {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(in.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            long sum = 0;
            for(int j = 0; j < n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
                if(j % 2 == 0){
                    sum += arr[j];
                }
            }
            //even
            int end = ((n - 1) % 2 == 0) ? n - 1 : n - 2;
            long LM = Long.MIN_VALUE + Integer.MAX_VALUE, GM = Long.MIN_VALUE + Integer.MAX_VALUE;
            for(int j = 2; j <= end; j += 2){
                int A = arr[j - 1] - arr[j];
                LM = Math.max(A, A + LM);
                if(LM > GM){
                    GM = LM;
                }
            }
            LM = Long.MIN_VALUE + Integer.MAX_VALUE;
            end = ((n - 1) % 2 == 1) ? n - 1 : n - 2;
            for(int j = 1; j <= end; j += 2){
                int A = arr[j] - arr[j - 1];
                LM = Math.max(A, A + LM);
                if(LM > GM){
                    GM = LM;
                }
            }
            if(GM > 0){
                System.out.println(sum + GM);
            }
            else{
                System.out.println(sum);
            }
        }
        in.close();
    }
}
