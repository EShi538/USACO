import java.io.*;
import java.util.*;
public class A {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(in.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
                set.add(arr[j]);
            }
            if(set.size() == 1){
                System.out.println(arr.length);
            }
            else{
                System.out.println(1);
            }
        }
    }
}
