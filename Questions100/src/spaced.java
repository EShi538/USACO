import java.io.*;
import java.util.*;
public class spaced {
    static int n;
    static int[][] m;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        m = new int[n][n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                m[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        in.close();
        int ansRows = 0;
        for(int i = 0; i < n; i++){ //check rows
            int sum = 0, sumOffset = 0;
            for(int j = 0; j < n; j++){
                if(j % 2 == 0){
                    sum += m[i][j];
                }
                else{
                    sumOffset += m[i][j];
                }
            }
            ansRows += Math.max(sum, sumOffset);
        }
        int ansColumns = 0;
        for(int i = 0; i < n; i++){ //check columns
            int sum = 0, sumOffset = 0;
            for(int j = 0; j < n; j++){
                if(j % 2 == 0){
                    sum += m[j][i];
                }
                else{
                    sumOffset += m[j][i];
                }
            }
            ansColumns += Math.max(sum, sumOffset);
        }
        PrintWriter out = new PrintWriter(System.out);
        out.write(Integer.toString(Math.max(ansRows, ansColumns)));
        out.close();
    }
}
