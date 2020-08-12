import java.io.*;
import java.util.*;
public class C {
    static int n, m;
    static int[] A, B;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new int[n];
        B = new int[m];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < m; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }
        in.close();

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < 512; i++){
            boolean good = true;
            for(int j = 0; j < n; j++){
                boolean found = false;
                for(int k = 0; k < m; k++){
                    if(((A[j] & B[k]) | i) == i){
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    good = false;
                    break;
                }
            }
            if(good){
                System.out.println(i);
                break;
            }
        }
    }
}
