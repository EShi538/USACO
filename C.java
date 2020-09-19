import java.io.*;
import java.util.*;
public class C {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        int ind = 0;
        int[] ans = new int[n];
        for(int i = 1; i < n; i +=2 ){
            ans[i] = a[ind];
            ind++;
        }
        for(int i = 0; i < n; i += 2){
            ans[i] = a[ind];
            ind++;
        }
        int cnt = 0;
        for(int i = 1; i < n - 1; i++){
            if(ans[i] < ans[i - 1] && ans[i] < ans[i + 1]){
                cnt++;
            }
        }
        out.println(cnt);
        for(int i: ans){
            out.print(i + " ");
        }
        out.close();
    }
}
