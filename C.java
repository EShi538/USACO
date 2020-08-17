import java.io.*;
import java.util.*;
public class C {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(in.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            long ans = 0;
            for(int j = 1; j < n; j++){
                if(arr[j] < arr[j - 1]){
                    ans += arr[j - 1] - arr[j];
                }
            }
            System.out.println(ans);
        }
    }
}
