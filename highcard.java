import java.io.*;
import java.util.*;
public class highcard {
    static int n;
    static int[] e, b;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter out = new PrintWriter(new File("highcard.out"));
        n = Integer.parseInt(in.readLine());
        e = new int[n]; b = new int[n];
        for(int i = 0; i < n; i++){
            e[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        Arrays.sort(e);
        int ind = 0;
        for(int i = 1; i <= 2 * n; i++){
            if(!(Arrays.binarySearch(e, i) >= 0)){
                b[ind] = i;
                ind++;
            }
        }
        int pe = 0, pb = 0;
        int ans = 0;
        while(pe < n && pb < n){
            if(b[pb] > e[pe]){
                pb++; pe++;
                ans++;
            }
            else{
                pb++;
            }
        }
        out.println(ans);
        out.close();
    }
}
