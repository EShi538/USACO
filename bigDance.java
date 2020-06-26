import java.util.Arrays;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static int[] orig = new int[n];
    static int sum = 0;

    public static void rec(int[] arr){
        if(arr.length == 1){
            return;
        }
        else if(arr.length == 2){
            sum = sum + (arr[0] * arr[1]);
            return;
        }
        if(arr.length % 2 == 1){
            int[] a = Arrays.copyOfRange(arr, 0, arr.length/2 + 1);
            int[] b = Arrays.copyOfRange(arr, arr.length/2 + 1, arr.length);
            rec(a);
            rec(b);
        }
        else if(arr.length % 2 == 0){
            int[] a = Arrays.copyOfRange(arr, 0, arr.length/2);
            int[] b = Arrays.copyOfRange(arr, arr.length/2, arr.length);
            rec(a);
            rec(b);
        }
        return;
    }

    
    public static void main(String[] args) throws Exception {
        for(int i = 1; i <= n; i++){
            orig[i - 1] = i;
        }
        rec(orig);
        System.out.println(sum);
    }
}
