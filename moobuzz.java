import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class moobuzz {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("moobuzz.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        in.close();

        long lb = 0;
        long ub = Integer.MAX_VALUE;
        while(lb != ub){
            long m = (lb + ub)/2;
            if(check(m, n) == -2){
                m--;
            }
            if(check(m, n) == 1){
                ub = m - 1;
            }
            else if(check(m, n) == -1){
                lb = m + 1;
            }
            else{
                if(m % 5 == 0 || m % 3 == 0){
                    if(m % 15 == 10 || m % 15 == 6){
                        ub = m - 2;
                        lb = m - 2;
                    }
                    else {
                        ub = m - 1;
                        lb = m - 1;
                    }
                }
                else{
                    ub = m;
                    lb = m;
                }
            }
        }

        File file = new File("moobuzz.out");
        FileWriter writer = new FileWriter(file);
        writer.write(Long.toString(lb));
        writer.close();
    }

    public static int check(long x, int n){
        long tot = x/3 + x/5 - x/15;
        if(x - tot > n){
            return 1;
        } 
        else if(x - tot < n){
            return -1;
        }
        else{
            return 0;
        }
    }
}
