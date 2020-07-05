import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        List<Integer> cows = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            cows.add(Integer.parseInt(in.readLine()));
        }
        in.close();

        int cnt = 0;
        int balance = 0;
        int owe = 0;
        int pos = 0;
        boolean backtracking = false;
        while(true){
            cnt++;
            if(owe == 0 && pos == n - 1){
                break;
            }
            if(backtracking == false){
                if(cows.get(pos) < 0){
                    owe = owe + cows.get(pos);
                    if(balance >= -(owe) && balance > 0 && owe < 0){
                        backtracking = true;
                        cnt--;
                        continue;
                    }
                    pos++;
                    continue;
                }
                else{
                    balance = balance + cows.get(pos);
                    cows.set(pos, 0);
                    if(balance >= -(owe) && balance > 0 && owe < 0){
                        backtracking = true;
                        pos--;
                        continue;
                    }
                    else{
                        pos++;
                    }
                    continue;
                }
            }
            else{
                if(-(owe) == 0){
                    backtracking = false;
                    pos = pos + 2;
                    cnt--;
                    continue;
                }
                if(cows.get(pos) != 0){
                    owe = owe - cows.get(pos);
                    balance = balance + cows.get(pos);
                    cows.set(pos, 0);
                    pos--;
                    continue;
                }
                else{
                    pos--;
                    continue;
                }
            }
        }
        System.out.println(cnt);
    }
}
