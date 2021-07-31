import java.io.*;
import java.util.*;
public class maxcross{
    static int n, k, b;
    static int[] broken;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        broken = new int[b];
        for(int i = 0; i < b; i++){
            broken[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        Arrays.sort(broken);
        int[] prefix = new int[n];
        int sum = 0; int pointer = 0;
        for(int i = 1; i <= n; i++){
            if(pointer < b && i == broken[pointer]){
                sum++;
                pointer++;
            }
            prefix[i - 1] = sum;
        }
        int min = -1;
        for(int i = k - 1; i < n; i++){
            int brokenCnt = prefix[i] - prefix[i - k + 1];
            if(min == -1 || brokenCnt < min){
                min = brokenCnt;
            }
        }
        FileWriter out = new FileWriter("maxcross.out");
        out.write(Integer.toString(min));
        out.close();
    }
}