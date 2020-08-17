import java.io.*;
import java.util.*;
public class B {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            int[] arr = new int[n];
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int max = Integer.MIN_VALUE;
            for(int j: arr){
                max = Math.max(j, max);
            }
            int[] A = new int[n], B = new int[n];
            for(int j = 0; j < n; j++){
                A[j] = max - arr[j];
            }
            max = Integer.MIN_VALUE;
            for(int j: A){
                max = Math.max(j, max);
            }
            for(int j = 0; j < n; j++){
                B[j] = max - A[j];
            }
            if(k % 2 == 0){
                for(int j: B){
                    System.out.print(j + " ");
                }
            }
            else{
                for(int j: A){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }
}
