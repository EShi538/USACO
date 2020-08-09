import java.io.*;
import java.util.*;
public class maxmed {
    static int n, k;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        in.close();

        System.out.println(solve());
    }
    static long solve(){
        long lb = arr[n/2], ub = Integer.MAX_VALUE, ans = -1;
        while(lb != ub){
            long m = (lb + ub)/2;
            if(good(m)){
                lb = m + 1;
                ans = Math.max(ans, m);
            }
            else{
                ub = m;
            }
        }
        return ans;
    }
    static boolean good(long guess){
        int mid = n/2, sum = 0;
        if(arr[mid] > guess){
            int x = 0;
            return false;
        }
        for(int i = mid; i < n; i++){
            if(guess - arr[i] > 0){
                sum += guess - arr[i];
            }
            if(sum > k){
                int x = 0;
                return false;
            }
        }
        int x= 0;
        return true;
    }
}
