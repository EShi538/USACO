import java.io.*;
import java.util.*;
public class permutation {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            int t = Integer.parseInt(in.readLine());
            for(int j = 1; j <= t; j++){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
