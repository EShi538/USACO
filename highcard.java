import java.util.*;
import java.io.*;
public class highcard {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("highcard.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        int[] elsieCards = new int[n];
        int[] bessieCards = new int[n];
        boolean[] taken = new boolean[2*n + 1];
        for(int i = 0; i < n; i++){
            elsieCards[i] = Integer.parseInt(in.readLine());
            taken[elsieCards[i]] = true;
        }
        in.close();

        int index = 0;
        for(int i = 1; i <= 2 * n; i++){
            if(taken[i] == false){
                bessieCards[index] = i;
                index++;
            }
        }
        Arrays.sort(bessieCards);
        Arrays.sort(elsieCards);
        int bessieInd = 0;
        int elsieInd = 0;
        int ans = 0;
        while(bessieInd < n && elsieInd < n){
            if(bessieCards[bessieInd] > elsieCards[elsieInd]){
                bessieInd++;
                elsieInd++;
                ans++;
            }
            else{
                bessieInd++;
            }
        }
        File out = new File("highcard.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}
