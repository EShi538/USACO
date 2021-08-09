import java.io.*;
import java.util.*;
public class pails {
    static int x, y, k, m;
    static boolean[][] visited; //boolean[a][b]
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("pails.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        in.close();
        visited = new boolean[101][101];
        check(0, 0, 0);
        FileWriter out = new FileWriter("pails.out");
        out.write(Integer.toString(ans));
        out.close();
    }
    static void check(int a, int b, int operations){
        ans = Math.min(Math.abs(m - (a + b)), ans);
        visited[a][b] = true;
        for(int i = 0; i < 6; i++){ 
            int[] pair = doOperation(i, a, b);
            int na = pair[0], nb = pair[1];
            if(!visited[na][nb] && operations + 1 <= k){
                check(na, nb, operations + 1);
                visited[na][nb] = false;
            }
        }
    }
    static int[] doOperation(int i, int a, int b){
        switch(i){
            case 0: //fill up pail A
                a = x;
                break;
            case 1: //fill up pailB
                b = y;
                break;
            case 2: //empty pail A
                a = 0;
                break;
            case 3: //empty pail B
                b = 0;
                break;
            case 4: //A pour into B
                if(a + b >= y){
                    a -= y - b;
                    b = y;
                }
                else{
                    b += a;
                    a = 0;
                }
                break;
            case 5: //B pour into A
                if(a + b >= x){
                    b -= x - a;
                    a = x;
                }
                else{
                    a += b;
                    b = 0;
                }
                break;
        }
        int[] ans = new int[2];
        ans[0] = a; ans[1] = b;
        return ans;
    }
}
