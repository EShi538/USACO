import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String type = in.next();
        int cnt = 0;
        int closeCnt = 0;
        int ans = 0;
        int actCnt = 0;
        for(int i = 0; i < type.length(); i++){
            if(type.charAt(i) == '('){
                cnt++;
                actCnt++;
            }
            else{
                cnt--;
                actCnt--;
                closeCnt++;
            }
            if(cnt < 0){
                ans = ans + closeCnt;
                cnt = cnt + 2;
            }
            //System.out.print(cnt);
        }
        cnt = 0;
        if(actCnt > 0){
            //System.out.print("I entered the other way!");
            StringBuilder input = new StringBuilder(type);
            input.reverse();
            for(int i = 0; i < input.length(); i++){
                if(input.charAt(i) == '('){
                    input.setCharAt(i, ')');
                }
                else{
                    input.setCharAt(i, '(');
                }
            }
            cnt = 0;
            closeCnt = 0;
            type = input.toString();
            for(int i = 0; i < type.length(); i++){
                if(type.charAt(i) == '('){
                    cnt++;
                }
                else{
                    cnt--;
                    closeCnt++;
                }
                if(cnt < 0){
                    ans = ans + closeCnt;
                    cnt = cnt + 2;
                }
            }
        }
        System.out.println(ans);
        in.close();
    }
}
