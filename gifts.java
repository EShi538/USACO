import java.io.*;
import java.util.*;
public class gifts {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            int[] b = new int[n];
            int minA = Integer.MAX_VALUE;
            int minB = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                a[j] = Integer.parseInt(st.nextToken());
                minA = Math.min(minA, a[j]);
            }
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                b[j] = Integer.parseInt(st.nextToken());
                minB = Math.min(minB, b[j]);
            }
            long ans = 0;
            for(int j = 0; j < n; j++){
                int currA = a[j]; int currB = b[j];
                if(a[j] > minA && b[j] > minB){
                    if(a[j] - minA > b[j] - minB){
                        currA -= b[j] - minB;
                        ans += (b[j] - minB) + (currA - minA);
                    }
                    else if(a[j] - minA < b[j] - minB){
                        currB -= a[j] - minA;
                        ans += (a[j] - minA) + (currB - minB);
                    }
                    else{
                        ans += b[j] - minB;
                    }
                }
                else if(a[j] > minA && b[j] == minB){
                    ans += a[j] - minA;
                }
                else if(a[j] == minA && b[j] > minB){
                    ans += b[j] - minB;
                }
            }
            System.out.println(ans);
        }
    }
}
