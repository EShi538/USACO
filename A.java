import java.io.*;
import java.util.*;
public class A {
    static int t;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int[] p = new int[n];
            int[] sum = new int[n - 1];
            for(int j = 0; j < n; j++){
                p[j] = Integer.parseInt(st.nextToken());
            }
            for(int j = 0; j < n/2; j++){
                int tmp = p[j];
                p[j] = p[n - 1 - j];
                p[n - 1 - j] = tmp;
            }
            for(int j: p){
                out.print(j + " ");
            }
            out.println();
        }
        out.close();
    }
    static boolean valid(int[] c){
        Set<Integer> s = new HashSet<>();
        for(int i: c){
            s.add(i);
        }
        if(s.size() != c.length){
            return false;
        }
        for(int i: c){
            if(i <= 0 || i > c.length){
                return false;
            }
        }
        return true;
    }

}
