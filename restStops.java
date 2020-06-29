import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int n = in.nextInt();
        BigInteger r_b = in.nextBigInteger();
        BigInteger r_f = in.nextBigInteger(); 
        BigInteger r_g = r_b.subtract(r_f); //seconds gained per meter
        List<stop> stops = new ArrayList<stop>();
        for(int i = 0; i < n; i++){
            int pos = in.nextInt();
            stops.add(new stop(pos, in.nextInt()));
        }
        in.close();
        Collections.sort(stops, new sort());
        BigInteger[] r_Max = new BigInteger[l + 1];
        int ind = n - 1;
        BigInteger curr = BigInteger.ZERO;
        for(int i = l; i >= 0; i--){
            if(ind > -1 && i == stops.get(ind).pos){
                if(stops.get(ind).tastiness > curr.longValue()){
                    curr = BigInteger.valueOf(stops.get(ind).tastiness);
                }
                ind--;
            }
            r_Max[i] = curr;
        }
        BigInteger total = BigInteger.ZERO;
        for(int i = 1; i <= l; i++){
            total = total.add((r_Max[i].multiply(r_g)));
        }
        System.out.println(total);
    }
}

class stop{
    int pos;
    int tastiness;
    public stop(int pos, int tastiness){
        this.pos = pos;
        this.tastiness = tastiness;
    }
}

class sort implements Comparator<stop> { 
    public int compare(stop a, stop b){ 
        return a.pos - b.pos; 
    } 
} 