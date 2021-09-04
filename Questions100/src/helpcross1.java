import java.io.*;
import java.util.*;
public class helpcross1 {
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
        List<crosser1> cows = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            cows.add(new crosser1(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        in.close();
        Arrays.sort(chickens);
        Collections.sort(cows, new sortB());
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
class crosser1{
    int A;
    int B;
    public crosser1(int A, int B){
        this.A = A;
        this.B = B;
    }
}
class sortB implements Comparator<crosser1>{
    public int compare(crosser1 a, crosser1 b){
        return a.B - b.B;
    }
}