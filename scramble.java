import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] names = new String[n];
        String[] low = new String[n];
        String[] high = new String[n];

        for(int i = 0; i < n; i++){
            String word = in.next();      
            names[i] = word;
            char[] chArr = word.toCharArray();
            Arrays.sort(chArr);
            low[i] = new String(chArr);
            high[i] = new StringBuilder(low[i]).reverse().toString();
        }

        Arrays.sort(low);
        Arrays.sort(high);

        for(int i = 0; i < n; i++){
            String word = names[i];
            char[] chArr = word.toCharArray();
            Arrays.sort(chArr);
            String word2 = new String(chArr);
            String word1 = new StringBuilder(word2).reverse().toString();

            int x = Arrays.binarySearch(high, word2);
            if(x < 0){
                x = -(x + 1);
            }
            x++;

            int y = Arrays.binarySearch(low, word1);
            if(y < 0){
                y = -(y + 1);
            }
            else{
                y++;
            }
            System.out.println(x + " " + y);
        }
        in.close();
    }
}

