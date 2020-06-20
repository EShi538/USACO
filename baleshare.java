import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int ans = Integer.MAX_VALUE;
    static List<Integer> bales = new ArrayList<Integer>();
    public static void solve(int barn1, int barn2, int barn3, int ind){
        int MaxBarn = Math.max(barn1, Math.max(barn2, barn3));
        if(MaxBarn >= ans){
            return;
        }
        if(ind == n){
            ans = MaxBarn;
            return;
        }
        solve(barn1 + bales.get(ind), barn2, barn3, ind + 1);
        solve(barn1, barn2 + bales.get(ind), barn3, ind + 1);
        solve(barn1, barn2, barn3 + bales.get(ind), ind + 1);
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < n; i++){
            bales.add(in.nextInt());
        }
        solve(0, 0, 0, 0);
        System.out.println(ans);
        in.close();
    }
}