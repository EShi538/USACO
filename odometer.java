import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        String num = "";
        
        int cnt = 0;
        List<Long> dup = new ArrayList<Long>();
        for(int k = Math.min(a.length(), b.length()); k <= Math.max(a.length(), b.length()); k++){
            num = "";
            for(int i = 0; i < k; i++){
                num = num + "0";
            }
            String repl = num;
            for(int i = 0; i < 10; i++){
                num = num.replace('0', (char)(i + 48));
                for(int j = 0; j < k; j++){
                    for(char e = '0'; e <= '9'; e++){
                        if(j == 0 && e == '0'){
                            continue;
                        }
                        if(i == 0 && j != 0){
                            continue;
                        }
                        if(Integer.parseInt(Character.toString(e)) == i){
                            continue;
                        }
                        StringBuilder input = new StringBuilder(num);
                        input.setCharAt(j, e);
                        if(Long.parseLong(input.toString()) <= Long.parseLong(b) && Long.parseLong(input.toString()) >= Long.parseLong(a)){
                            if(dup.contains(Long.parseLong(input.toString()))){
                                System.out.println("ALERT!!!    " + Long.parseLong(input.toString()));
                            }
                            else{
                                dup.add(Long.parseLong(input.toString()));
                            }
                            cnt++;
                        }
                    }
                }
                num = repl;
            }
        }
        
        System.out.println(cnt);
        in.close();
    }
}
