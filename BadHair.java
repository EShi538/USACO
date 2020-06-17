import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] cows = new int[n];
        for(int i = 0; i < n; i++){
            cows[i] = in.nextInt();
        }
        in.close();

        Stack<Integer> q = new Stack<Integer>();

        long sum = 0;
        for(int i = 0; i < n; i++){
            while(q.size() != 0 && q.peek() <= cows[i]){
                q.pop();
            }
            sum = sum + q.size();
            q.add(cows[i]);
        }

        System.out.print(sum);
    }
}