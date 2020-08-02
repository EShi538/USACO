import java.io.*;
import java.util.*;
public class whatbase {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("whatbase.in");
        BufferedReader in = new BufferedReader(reader);
        File out = new File("whatbase.out");
        PrintWriter writer = new PrintWriter(out);
        int n = Integer.parseInt(in.readLine());
        List<pair> pairs = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pairs.add(new pair(a, b));
        }
        in.close();

        int[][] convertA = new int[n][15001];
        int[][] convertB = new int[n][15001];
        for(int i = 0; i < n; i++) {
            pair curr = pairs.get(i);
            for(int base = 10; base <= 15000; base++) {
                convertA[i][base] = convert(curr.a, base);
                convertB[i][base] = convert(curr.b, base);
            }
        }
        for(int i = 0; i < n; i++){
            for(int base = 10; base <= 15000; base++){
                int pos = Arrays.binarySearch(convertB[i], convertA[i][base]);
                if(pos >= 0){
                    writer.println(base + " " + pos);
                }
            }
        }
        writer.close();
    }
    static int convert(int x, int base){
        int num = 0;
        int[] digits = new int[3];
        String word = Integer.toString(x);
        for(int i = 2; i >= 0; i--){
            digits[i] = word.charAt(i) - 48;
        }
        for(int i = 2; i >= 0; i--){
            num += digits[2 - i] * Math.pow(base, i);
        }
        return num;
    }
}

class pair{
    int a, b;
    public pair(int a, int b){
        this.a = a; this.b = b;
    }
}