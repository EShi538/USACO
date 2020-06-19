import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<cow> cows = new ArrayList<cow>();
        for(int i = 0; i < n; i++){
            cows.add(new cow(in.nextInt(), in.nextInt()));
        }
        in.close();

        int ans = 0;
        int j = n - 1;
        while(j >= 0){
            cow st = cows.get(j);
            ans++;
            j--;
            while(j >= 0 && cows.get(j).spd > st.spd){
                j--;
            }
        }
        System.out.println(ans);

    }
}

class cow{
    int pos;
    int spd;
    public cow(int pos, int spd){
        this.pos = pos;
        this.spd = spd;
    }
}