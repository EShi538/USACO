import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0; i < n; i++){
            boolean cont = false;
            Stack<Character> st = new Stack<Character>();
            int k = in.nextInt();
            String dance = in.next();
            for(int j = 0; j < k; j++){
                if(dance.charAt(j) == '>'){
                    st.add('>');
                }
                else{
                    if(st.size() == 0){
                        System.out.println("illegal");
                        cont = true;
                        break;
                    }
                    else{
                        st.pop();
                    }
                }
            }
            if(cont == true){
                continue;
            }
            if(st.size() != 0){
                System.out.println("illegal");
            }
            else{
                System.out.println("legal");
            }
        }
        in.close();
    }
}
