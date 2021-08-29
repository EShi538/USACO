import java.io.*;
import java.util.*;
public class cardgame {
    static int n;
    static int[] elsie, bessie;
    static TreeSet<Integer> elsieSet = new TreeSet<>(), bessieSet = new TreeSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
        n = Integer.parseInt(in.readLine());
        elsie = new int[n]; bessie = new int[n];
        for(int i = 0; i < n; i++){
            elsie[i] = Integer.parseInt(in.readLine());
            elsieSet.add(elsie[i]);
        }
        in.close();
        int index = 0;
        for(int i = 1; i <= 2 * n; i++){
            if(elsieSet.contains(i)){
                continue;
            }      
            if(index < n){
                bessie[index] = i;
                bessieSet.add(i);
                index++;
            }
        }
        int ans = 0;
        int[] elsieSecondHalf = new int[n/2];
        for(int i = 0; i < n/2; i++){
            elsieSecondHalf[i] = elsie[n - i - 1];
        }
        Arrays.sort(elsieSecondHalf);
        int bessiePointer = 0, elsiePointer = 0;
        while(bessiePointer < n/2 && elsiePointer < n/2){
            if(bessie[bessiePointer] < elsieSecondHalf[elsiePointer]){
                bessiePointer++; elsiePointer++;
                ans++;
                continue;
            }
            else{
                elsiePointer++;
            }
        }
        bessiePointer = n/2; elsiePointer = n/2;
        int[] elsieFirstHalf = new int[n];
        for(int i = 0; i < n/2; i++){
            elsieFirstHalf[i] = elsie[i];
        }
        Arrays.sort(elsieFirstHalf);
        for(int i = 0; i <= n/4; i++){
            int tmp = elsieFirstHalf[i];
            elsieFirstHalf[i] = elsieFirstHalf[n/2 - i - 1];
            elsieFirstHalf[n/2 - i - 1] = tmp;
        }
        while(bessiePointer < n && elsiePointer < n){
            if(bessie[bessiePointer] > elsieFirstHalf[elsiePointer]){
                bessiePointer++; elsiePointer++;
                ans++;
                continue;
            }
            else{
                elsiePointer++;
            }
        }
        FileWriter out = new FileWriter("cardgame.out");
        out.write(Integer.toString(ans));
        out.close();
    }
}
