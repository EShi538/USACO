import java.io.*;
import java.util.*;
public class robotMove {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 1; tst <= t; tst++){
            long xc = 0, yc = 0;
            String d = in.readLine();
            long m = 1;
            Stack<Long> st = new Stack<>();
            st.add(m);
            for(int i = 0; i < d.length(); i++){
                if(d.charAt(i) > 65 && d.charAt(i) < 90){
                    switch (d.charAt(i)){
                        case 'N': yc -= m; yc %= (int)1e9; break;
                        case 'S': yc += m; yc %= (int)1e9; break;
                        case 'W': xc -= m; xc %= (int)1e9; break;
                        case 'E': xc += m; xc %= (int)1e9; break;
                    }
                }
                else if(d.charAt(i) > 49 && d.charAt(i) < 58){
                    m *= (d.charAt(i) - 48);
                    m %= (int)1e9;
                    st.add(m);
                }
                else if(d.charAt(i) == ')'){
                    st.pop();
                    m = st.peek();
                }
            }
            long x = xc + 1, y = yc + 1;
            if(x > (int)1e9){
                x = x % (int)1e9 + 1;
            }
            else if(x <= 0){
                x = (int)1e9 + (x % (int)1e9);
            }
            if(y > (int)1e9){
                y = y % (int)1e9 + 1;
            }
            else if(y <= 0){
                y = (int)1e9 + (y % (int)1e9);
            }
            out.println("Case #" + tst + ": " + x + " " + y);
        }
        out.close();
    }
}