import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> baseball = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            baseball.add(in.nextInt());
        }
        in.close();

        Collections.sort(baseball);
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                for(int e = j + 1; e < n; e++){
                    int x = baseball.get(i);
                    int y = baseball.get(j);
                    int z = baseball.get(e);
                    int throw1 = Math.abs(x - y);
                    int throw2 = Math.abs(z - y);
                    if(throw2 <= 2 * throw1 && throw2 >= throw1){
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
