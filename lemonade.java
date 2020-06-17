import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class lemonade {
    public static void main(String[] args) throws Exception {
        FileReader file = new FileReader("lemonade.in");
        Scanner in = new Scanner(file);
        int n = in.nextInt();
        int[] cows = new int[n];
        for(int i = 0; i < n; i++){
            cows[i] = in.nextInt();
        }
        Arrays.sort(cows);
        List<Integer> line = new ArrayList<Integer>();
        for(int i = cows.length - 1; i >= 0; i--){
            int curr = cows[i];
            if(curr >= line.size()){
                line.add(curr);
            }
        }
        File file3 = new File("lemonade.out");
		FileWriter writer = new FileWriter(file3);
        writer.write(Integer.toString(line.size()));
        in.close();
        writer.close();
    }
}
