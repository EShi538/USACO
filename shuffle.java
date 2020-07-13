import java.util.*;
import java.io.*;
public class shuffle {
    static boolean[] removed;
    static int[] parentNum;
    public static void solve(int curr, int[] children){
        if(parentNum[curr] == 0){
            removed[curr] = true;
            parentNum[children[curr]]--;
            solve(children[curr], children);
        }
        return;
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("shuffle.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] children = new int[n + 1];
        removed = new boolean[n + 1];
        parentNum = new int[n + 1];
        for(int i = 0; i <= n; i++){
        }
        for(int i = 1; i <= n; i++){
            int pos = Integer.parseInt(st.nextToken());
            children[i] = pos;
            parentNum[pos]++;
        }       
        in.close();
        for(int i = 1; i <= n; i++){
            if(removed[i] == false){
                solve(i, children);
            }
        }  
        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(removed[i] == false){
                ans++;
            }
        }
        File out = new File("shuffle.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}