import java.io.*;
public class moobuzz{
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("moobuzz.in"));
        n = Integer.parseInt(in.readLine());
        in.close();
        long l = 1, r = Integer.MAX_VALUE - 1;
        while(l < r){
            long m = (l + r)/2;
            long place = m - mooCnt(m);
            if(place < n){
                l = m;
            }
            else if(place > n){
                r = m - 1;
            }
            else{
                if(m % 15 == 0){
                    l = m - 1;
                    break;
                }
                if(m % 3 == 0){
                    if(m/3 % 5 == 2){
                        l = m - 2;
                        break;
                    }
                    else{
                        l = m - 1;
                        break;
                    }
                }
                if(m % 5 == 0){
                    if(m/5 % 3 == 2){
                        l = m - 2;
                        break;
                    }
                    else{
                        l = m - 1;
                        break;
                    }
                }
                l = m;
                break;
            }
        }
        FileWriter out = new FileWriter("moobuzz.out");
        out.write(Long.toString(l));
        out.close();
    }
    static long mooCnt(long num){
        long cnt3 = num/3;
        long cnt5 = num/5;
        long cnt15 = num/15;
        return cnt3 + cnt5 - cnt15;
    }
}