import java.io.*;

public class cyclic {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(in.readLine());
        long a = 1;
        for(int i = 1; i <= x; i++){
            a = (a * i) % ((int)(1e9 + 7));
        }
        long b = 1;
        for(int i = 1; i < x; i++){
            b = (b * 2) % ((int)(1e9 + 7));
        }
        System.out.println((a - b + (int)(1e9 + 7)) % ((int) 1e9 + 7));
        in.close();
    }
}
