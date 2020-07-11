import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class herding {
    public static void main(String[] arg) throws Exception{
        //Input
        FileReader reader = new FileReader("herding.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        int[] cows = new int[n];
        for(int i = 0; i < n; i++){
            cows[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        Arrays.sort(cows);
        int[] gaps = new int[n - 1];
        for(int i = 0; i < n - 1; i++){
            gaps[i] = (cows[i + 1] - cows[i]) - 1; 
        }
        
        //prefix sums on gaps
        int[] gapPS = new int[n - 1];
        for(int i = 0; i < n - 1; i++){
            if(i == 0){
                gapPS[0] = gaps[0];
                continue;
            }
            gapPS[i] = gapPS[i - 1] + gaps[i];
        }

        File out = new File("herding.out");
        FileWriter writer = new FileWriter(out);
        //find min
        int best = -1;
        boolean cont = false;
        if((cows[n - 2] - cows[0] == n - 2 && cows[n - 1] - cows[n - 2] > 2) || (cows[n - 1] - cows[1] == n - 2 && cows[1] - cows[0] > 2)){
            cont = true;
            writer.write("2" + "\n");
        }
        if(cont == false){
            for(int i = 0; i < n; i++){
                int j = i;
                while(j < n - 1 && cows[j + 1] - cows[i] <= n - 1){
                    j++;
                }
                int check = j - i + 1;
                if(check > best){
                    best = check;
                }
            }
            writer.write(Integer.toString(n - best) + "\n");
        }

        //find max
        int a = gapPS[n - 3];
        int b = gapPS[n - 2] - gapPS[0];
        if(a >= b){
            writer.write(Integer.toString(a));
        }
        else{
            writer.write(Integer.toString(b));
        }
        writer.close();
    }
}