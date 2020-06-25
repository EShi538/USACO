import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Haybales {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//input
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		int[] haybales = new int[N + 1];
		for(int i = 0; i < K; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			haybales[a]++;
			if(b != N) {
				haybales[b + 1]--;
			}
			else {
				continue;
			}
		}
		int[] PS = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			PS[i] = PS[i - 1] + haybales[i];
		}
		Arrays.sort(PS);
		System.out.println(PS[N/2 + 1]);
		in.close();
	}
}

class ReverseSort implements Comparator<Integer> { 
    public int compare(Integer a, Integer b){ 
        return a - b; 
    } 
} 

