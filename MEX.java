import java.io.*;
import java.util.*;
public class MEX {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        int max = -1;
        Set<Integer> set = new TreeSet<>();
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
            max = Math.max(arr[i], max);
        }
        in.close();

        int currSection = 0;
        int num = 0;
        List<Integer> sections = new ArrayList<>(set);
        sections.add(n);
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            if(i != 0) {
                if (arr[i] != arr[i - 1]) {
                    ans[i] = sections.get(currSection);
                    currSection++;
                    continue;
                }
            }
            while(set.contains(num)){
                num++;
            }
            ans[i] = num;
            num++;
        }
        for(int i: ans){
            System.out.print(i + " ");
        }
    }
}
