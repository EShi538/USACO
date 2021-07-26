import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class helpcross {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("helpcross.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] chickens = new int[c];
        boolean[] used = new boolean[c];
        for(int i = 0; i < c; i++){
            chickens[i] = Integer.parseInt(in.readLine());
        }
        List<crosser> cows = new ArrayList<crosser>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            cows.add(new crosser(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        in.close();
        Arrays.sort(chickens);
        Collections.sort(cows, new sortA());
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < c; j++){
                if(chickens[j] <= cows.get(i).B && chickens[j] >= cows.get(i).A && used[j] == false){
                    used[j] = true;
                    cnt++;
                    break;
                }   
            }
        }
        File out = new File("helpcross.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(cnt));
        writer.close();
    }
}

class crosser{
    int A;
    int B;
    public crosser(int A, int B){
        this.A = A;
        this.B = B;
    }
}

class sortA implements Comparator<crosser>{
    public int compare(crosser a, crosser b){
        return a.B - b.B;
    }
}