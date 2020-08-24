import java.io.*;
import java.util.*;
public class Solution {
    static int n;
    static long[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        arr = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(in.readLine());
        }
        TreeSet<Long> s = new TreeSet<>();
        for(int i = 0; i < n; i++){
            if(arr[i] == -1){
                continue;
            }
            if(i == 0){
                if(arr[i + 1] == -1){
                    s.add(arr[i]);
                }
            }
            else if(i == n - 1){
                if(arr[i - 1] == -1){
                    s.add(arr[i]);
                }
            }
            else{
                if(arr[i - 1] == -1 || arr[i + 1] == -1){
                    s.add(arr[i]);
                }
            }
        }
        long sum = s.first() + s.last();
        if(s.size() == 0){
            System.out.println(0);
            return;
        }
        long k = sum/2;
        for(int i = 0; i < n; i++){
            if(arr[i] == -1){
                arr[i] = k;
            }
        }
        long ans = 0;
        for(int i = 0; i < n - 1; i++){
            ans = Math.max(Math.abs(arr[i] - arr[i + 1]), ans);
        }
        System.out.println(ans);
        in.close();
    }
}