import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class fairphoto {

	public static void main(String[] args) {
		//input
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		List<vache> cows = new ArrayList<vache>();
		for(int i = 0; i < n; i++) {
			cows.add(new vache(in.nextInt(), in.next().charAt(0)));
		}
		in.close();
		
		//code
		Collections.sort(cows, new sortPosition());
        HashMap<Integer, Integer> used = new HashMap<Integer, Integer>(); 
		int[] checks = new int[n];
		for(int i = 0; i < n; i++) {
			if(cows.get(i).breed == 'G') {
				checks[i] = 1;
			}
			else {
				checks[i] = -1;
			}
		}
		int[] PS = new int[n + 1];
		int[] positions = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			PS[i] = PS[i - 1] + checks[i - 1];
			positions[i] = cows.get(i - 1).position;
		}
		int max = Integer.MIN_VALUE;
		used.put(0, 0);
		for(int i = 1; i <= n; i++) {
			if(used.containsKey(PS[i])) {
				int dist = (positions[i] - positions[used.get(PS[i]) + 1]);
				if(dist > max) {
					max = dist;
				}
			}
			else {
				used.put(PS[i], i);
			}
		}
		char first = cows.get(0).breed;
		int firstPosition = cows.get(0).position;
		int maxCnt = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			if (i == n - 1) {
				maxCnt = cows.get(i).position - firstPosition;
				break;
			}
			if(cows.get(i + 1).breed == first) {
				continue;
			}
			else {
				int distance = cows.get(i).position - firstPosition;
				first = cows.get(i + 1).breed;
				firstPosition = cows.get(i + 1).position;
				if(distance > maxCnt) {
					maxCnt = distance;
				}
				continue;
			}
		}
		if(maxCnt > max) {
			max = maxCnt;
		}
		System.out.println(max);
	}
}

class vache{
	int position;
	char breed;
	public vache(int position, char breed) {
		this.position = position;
		this.breed = breed;
	}
}

class sortPosition implements Comparator<vache> { 
    public int compare(vache a, vache b){ 
        return a.position - b.position; 
    } 
} 