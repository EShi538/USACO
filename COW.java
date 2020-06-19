import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Long n = in.nextLong();
        String word = in.next();
        Long Ccnt = (long) 0;
        Long COcnt = (long) 0;
        Long COWcnt = (long) 0;
        for(int i = 0; i < n; i++){
            int letter = word.charAt(i);
            if(letter == 'C'){
                Ccnt++;
            }
            else if(letter == 'O'){
                COcnt = COcnt + Ccnt;
            }
            else{
                COWcnt = COWcnt + COcnt;
            }
        }
        System.out.println(COWcnt);
        in.close();
    }
}
