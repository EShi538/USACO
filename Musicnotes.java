import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] time = new int[n];
        List<Integer> notes = new ArrayList<Integer>();
        List<Integer> queries = new ArrayList<Integer>();
        int prev = 0;
        for(int i = 0; i < n; i++){
            int note = in.nextInt();
            if(i != 0){
                time[i] = prev + time[i - 1];
            }
            else{
                time[0] = 0;
            }
            prev = note;
            notes.add(note);
        }
        for(int i = 0; i < k; i++){
            queries.add(in.nextInt());
        }
        in.close();

        for(int i = 0; i < k; i++){
            int query = queries.get(i);
            int pos = Arrays.binarySearch(time, query);
            if(pos >= 0){
                System.out.println(pos + 1);
            }
            else{
                pos = -(pos + 1); 
                System.out.println(pos);
            }
        }
    }
}
