import java.io.*;
public class uniSol {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int tst = 0; tst < t; tst++){
            String s = in.readLine();
            int R = 0, P = 0, S = 0;
            for(int i = 0; i < s.length(); i++){
                switch(s.charAt(i)) {
                    case 'R' : R++; break;
                    case 'P' : P++; break;
                    default: S++; break;
                }
            }
            int max = Math.max(Math.max(R, P), S);
            if(max == R && max == P && max == S){
                out.println(s);
            }
            else if(max == R){
                for(int i = 0; i < s.length(); i++){
                    out.print('P');
                }
                out.println();
            }
            else if(max == P){
                for(int i = 0; i < s.length(); i++) {
                    out.print('S');
                }
                out.println();
            }
            else{
                for(int i = 0; i < s.length(); i++) {
                    out.print('R');
                }
                out.println();
            }
        }
        out.close();
        in.close();
    }
}
