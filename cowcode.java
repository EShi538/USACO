import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class cowcode {
    static String word = "";
    static int length = 0;
    public static void main(String[] args) throws Exception {
        FileReader file = new FileReader("cowcode.in");
        BufferedReader in = new BufferedReader(file);
        StringTokenizer st = new StringTokenizer(in.readLine());
        word = st.nextToken();
        long n = Long.parseLong(st.nextToken());
        in.close();
        File file1 = new File("cowcode.out");
        FileWriter writer = new FileWriter(file1);
        writer.write(Character.toString(solve(word.length(), n)));
        writer.close();
    }
    public static char solve(long length, long n){
        while(length * 2 < n){
            length = length * 2;
        }
        length = length * 2;
        while(n > word.length()){
            if(length >= n){
                if(n == length/2){
                    n = length/2;
                    length = length/2;
                }
                else if(n == length/2 + 1){
                    n = length/2;
                }
                else if (n > length/2 + 1){
                    n = length/2 - (length - n + 1);
                }
                else{
                    length = length/2;
                }
            }
        }
        return word.charAt((int)(n - 1));
    }
}
