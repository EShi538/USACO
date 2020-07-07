import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class convention {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("convention.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arrivals = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            arrivals[i] = Integer.parseInt(st.nextToken());
        }
        in.close();

        Arrays.sort(arrivals);

        int lb = 0;
        int ub = (int)Math.pow(10, 9);
        int min = Integer.MAX_VALUE;
        while(lb != ub){
            int guess = (lb + ub)/2;
            if(busCnt(guess, arrivals, c) == m){
                if(guess < min){
                    min = guess;
                }
                ub = guess;
            }
            else if(busCnt(guess, arrivals, c) < m){
                ub = guess;
            }
            else{
                lb = guess + 1;
            }
        }
        File out = new File("convention.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(lb));
        writer.close();
    }

    public static int busCnt(int guess, int[] arrivals, int c){
        int cnt = 0;
        int st = arrivals[0];
        int ind = 0;
        while(ind < arrivals.length){
            int size = 0;
            while(ind < arrivals.length && arrivals[ind] <= st + guess && size < c){
                size++;
                ind++;
            }
            cnt++;
            if(ind < arrivals.length){
                st = arrivals[ind];
            }
        }
        return cnt;
    }
}
