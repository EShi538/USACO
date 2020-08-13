import java.io.*;
import java.util.*;
public class free {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        in.close();

        Arrays.sort(arr);
        Set<Integer> ans = new HashSet<>();
        for(int i: arr){
            if(i % k != 0){
                ans.add(i);
            }
            else if(!ans.contains(i/k)){
                ans.add(i);
            }
        }
        System.out.print(ans.size());
    }
}
