import java.math.*;
import java.util.*;
import java.io.*;

public class reststops {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("reststops.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        BigInteger r_b = new BigInteger(st.nextToken());
        BigInteger r_f = new BigInteger(st.nextToken());
        BigInteger r_g = r_b.subtract(r_f); //seconds gained per meter
        List<stop> stops = new ArrayList<stop>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int pos = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            stops.add(new stop(pos, c));
        }
        in.close();
        Collections.sort(stops, new sort7());
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
        FileWriter out = new FileWriter("reststops.out");
        out.write(total.toString());
        out.close();
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
class sort7 implements Comparator<stop> { 
    public int compare(stop a, stop b){ 
        return a.pos - b.pos; 
    } 
} 