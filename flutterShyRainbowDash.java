import java.io.*;
import java.util.*;
public class flutterShyRainbowDash {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int size = Integer.parseInt(in.readLine());
            if(size % 2 == 0){
                writer.println(size - (size/2 - 1));
            }
            else{
                writer.println(size - ((size - 1)/2));
            }
        }
        writer.close();
        in.close();
    }
}
