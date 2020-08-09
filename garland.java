import java.io.*;
import java.util.*;
public class garland {
    static int n;
    static String word;
    static int[][] prefixSums;
    static int[][] slidingPre;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        word = in.readLine();
        //0 -> not a; 1 -> not b; 2 -> not c... (char - 97)
        prefixSums = new int[26][n];
        slidingPre = new int[26][n + 1];
        for(char i = 'a'; i <= 'z'; i++){
            int[] PS = new int[n];
            for(int j = 0; j < n; j++){
                if(j == 0){
                    PS[j] = (word.charAt(0) != i) ? 1: 0;
                    continue;
                }
                PS[j] = (word.charAt(j) != i) ? PS[j - 1] + 1: PS[j - 1];
            }
            prefixSums[i - 97] = PS;
        }
        for(char i = 'a'; i <= 'z'; i++){
            for(int j = 1; j <= n; j++){
                slidingPre[i - 97][j] = slidingWindow(j, i);
            }
        }
        int q = Integer.parseInt(in.readLine());
        for(int i = 0; i < q; i++){
            String row = in.readLine();
            String[] tokens = row.split(" ");
            int m = Integer.parseInt(tokens[0]);
            char c = tokens[1].charAt(0);
            System.out.println(binSearch(m, c));
        }
        in.close();
    }
    static int binSearch(int m, char c){
        int lb = 0, ub = n + 1;
        int ans = -1;
        while(lb < ub){
            int guess = (lb + ub)/2;
            if(slidingPre[c - 97][guess] <= m){
                lb = guess + 1;
                ans = Math.max(ans, guess);
            }
            else{
                ub = guess;
            }
        }
        return ans;
    }
    static int slidingWindow(int rangeLength, char c){
        int l = 0, r = rangeLength - 1;
        int[] currPS = prefixSums[c - 97];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n - rangeLength + 1; i++){
            int notC = (i == 0) ? currPS[rangeLength - 1]: currPS[i + rangeLength - 1] - currPS[i - 1];
            min = Math.min(notC, min);
        }
        return min;
    }
}
