import java.io.*;
import java.util.*;


public class paintbarn {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("paintbarn.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] barn = new int[1001][1001];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            barn[y1][x1]++;
            barn[y1][x2]--;
            barn[y2][x1]--;
            barn[y2][x2]++;
        }
        in.close();

        int ans = 0;
        for(int i = 0; i < 1001; i++){
            for(int j = 0; j < 1001; j++){
                if(i == 0 && j == 0){
                    continue;
                }
                else if(i == 0 && j != 0){
                    barn[i][j] = barn[i][j] + barn[i][j - 1];
                }
                else if(i != 0 && j == 0){
                    barn[i][j] = barn[i][j] + barn[i - 1][j];
                }
                else{
                    barn[i][j] = barn[i][j - 1] + barn[i - 1][j] - barn[i - 1][j - 1] + barn[i][j];
                }
            }
        }
        for(int i = 0; i < 1001; i++){
            for(int j = 0; j < 1001; j++){
                if(barn[i][j] == k){
                    ans++;
                }
            }
        }
        File out = new File("paintbarn.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }        
}