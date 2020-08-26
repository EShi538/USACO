import java.io.*;
import java.util.*;
public class traders {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] customers = new String[n];
        for(int i = 0; i < n; i++){
            customers[i] = in.readLine();
        }
        in.close();
        Arrays.sort(customers);
        for(int i = 0; i < n; i++){
            int index = i;
            int cnt = 0;
            while(index < n && customers[index].equals(customers[i])){
                index++;
                cnt++;
            }
            double percentage = ((double)cnt/(double)n) * 100;
            if(percentage >= 5){
                System.out.println(customers[i]);
            }
            i = index - 1;
        }
    }
}
