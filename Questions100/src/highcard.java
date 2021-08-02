import java.io.*;
import java.util.*;
public class highcard {
    static int n;
    static int[] e, b;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
        n = Integer.parseInt(in.readLine());
        e = new int[n]; b = new int[n];
        for(int i = 0; i < n; i++){
            e[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        int index = 0;
        int index1 = 0;
        Arrays.sort(e);
        for(int i = 1; i <= 2 * n; i++){
            if(index < n && e[index] == i){
                index++;
                continue;
            }
            b[index1] = i;
            index1++;
        }
        Arrays.sort(b);
        int bi = 0, ei = 0;
        int ans = 0;
        while(bi < n && ei < n){
            if(b[bi] > e[ei]){
                bi++; ei++;
                ans++;
            }
            else{
                bi++;
            }
        }
        FileWriter out = new FileWriter("highcard.out");
        out.write(Integer.toString(ans));
        out.close();
    }
}
