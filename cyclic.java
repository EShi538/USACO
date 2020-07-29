import java.io.*;
import java.util.*;
public class cyclic {
    public static void main(String[] args)throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            String s = in.readLine();
            int max = -1;
            for(char j = '0'; j <= '9'; j++){
                for(char k = '0'; k <= '9'; k++){
                    if(j == '2' && k == '5'){
                        int x = 0;
                    }
                    int cnt = 0;
                    StringBuilder input = new StringBuilder();
                    for(int e = 0; e < s.length(); e++){
                        if(s.charAt(e) == j || s.charAt(e) == k){
                            input.append(s.charAt(e));
                        }
                    }
                    if(j != k) {
                        //jkjkjk
                        for (int e = 0; e < input.length() - 1; e++) {
                            if (input.charAt(e) == j && input.charAt(e + 1) == k) {
                                cnt += 2;
                                e++;
                            }
                        }
                        max = Math.max(cnt, max);
                        cnt = 0;
                        //kjkjkj
                        for (int e = 0; e < input.length() - 1; e++) {
                            if (input.charAt(e) == k && input.charAt(e + 1) == j) {
                                cnt += 2;
                                e++;
                            }
                        }
                        max = Math.max(cnt, max);
                    }
                    else{
                        max = Math.max(max, input.length());
                    }
                }
            }
            System.out.println(s.length() - max);
        }
    }
}
