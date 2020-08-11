import java.io.*;
import java.math.*;
import java.util.*;
public class tlevelLaserTPointer {
    static int n, u;
    static int[] energies;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        energies = new int[n];
        for(int i = 0; i < n; i++){
            energies[i] = Integer.parseInt(st.nextToken());
        }
        in.close();

        BigDecimal ans = BigDecimal.ONE.negate();
        //Calculation: BigDecimal energy = BigDecimal.valueOf(energies[r] - energies[i + 1]).divide(BigDecimal.valueOf(energies[r] - energies[i]), 20, RoundingMode.UP);
        int k = 0;
        for(int i = 0; i < n - 1; i++){
            k = Math.max(k, i);
            while(k < n && energies[k] - energies[i] <= u){
                k++;
            }
            k--;
            if(k == i || k == i + 1){
                continue;
            }
            BigDecimal amount = BigDecimal.valueOf(energies[k] - energies[i + 1]).divide(BigDecimal.valueOf(energies[k] - energies[i]), 20, RoundingMode.UP);
            if(amount.compareTo(ans) > 0){
                ans = amount;
            }
        }
        System.out.print(ans);
    }
}