import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); //#  patches of grass
        int k = in.nextInt(); //reach
        Map<Integer, Integer> field = new HashMap<Integer, Integer>(); //pos -> grass
        int max = -1;
        for(int i = 0; i < n; i++){
            int grass = in.nextInt();
            int pos = in.nextInt();
            field.put(pos, grass);
            if(pos > max){
                max = pos;
            }
        }
        in.close();

        //make prefix sum array
        int[] PS = new int[max + 1];
        for(int i = 0; i <= max; i++){
            if(i == 0){
                if(field.containsKey(i)){
                    PS[i] = field.get(i);
                }
                else{
                    PS[i] = 0;
                }   
            }
            else{
                if(field.containsKey(i)){
                    PS[i] = PS[i - 1] + field.get(i);
                }
                else{
                    PS[i] = PS[i - 1];
                }
            }
        }

        //sliding window
        int st = 0;
        int en = 0;
        int best = -1;
        if(k > max){
            System.out.println(PS[max + 1]);
        }
        for(int i = k; i <= max - k; i++){
            st = i - k;
            en = i + k;
            int grassAmount = 0;
            if(st != 0){
                grassAmount = PS[en] - PS[st - 1];
            }
            else{
                grassAmount = PS[en];
            }
            if(grassAmount > best){
                best = grassAmount;
            }
        }

        System.out.print(best);
    }
}
