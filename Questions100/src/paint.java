import java.io.*;
import java.util.*;
public class paint {
    static int n, q;
    static char[] fence;
    static int[] prefix, prefixReverse;
    static void findPrefix(){
        Stack<Character> activePaint = new Stack<>();
        int cnt = 0;
        for(int i = 0; i < n; i++){
            char currColor = fence[i];
            if((!activePaint.isEmpty() && currColor > activePaint.peek()) || activePaint.isEmpty()){
                activePaint.add(currColor);
                cnt++;
            }
            else if(!activePaint.isEmpty() && currColor < activePaint.peek()){
                boolean contains = false;
                while(!activePaint.isEmpty() && currColor <= activePaint.peek()){
                    if(activePaint.peek() == currColor){
                        contains = true;
                    }
                    activePaint.pop();
                }
                activePaint.add(currColor);
                if(!contains){
                    cnt++;
                }
            }
            prefix[i] = cnt;
        }
        cnt = 0;
        activePaint = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            char currColor = fence[i];
            if((!activePaint.isEmpty() && currColor > activePaint.peek()) || activePaint.isEmpty()){
                activePaint.add(currColor);
                cnt++;
            }
            else if(!activePaint.isEmpty() && currColor < activePaint.peek()){
                boolean contains = false;
                while(!activePaint.isEmpty() && currColor <= activePaint.peek()){
                    if(currColor == activePaint.peek()){
                        contains = true;
                    }
                    activePaint.pop();
                }
                activePaint.add(currColor);
                if(!contains){
                    cnt++;
                }
            }
            prefixReverse[i] = cnt;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken()); q = Integer.parseInt(st.nextToken());
        fence = new char[n];
        String row = in.readLine();
        for(int i = 0; i < n; i++){
            fence[i] = row.charAt(i);
        }
        prefix = new int[n]; prefixReverse = new int[n];
        findPrefix();
        PrintWriter out = new PrintWriter(System.out);
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if(a < 2 && b < n){
                out.println(Integer.toString(prefixReverse[b]));
                continue;
            }
            else if(a >= 2 && b == n){
                out.println(Integer.toString(prefix[a - 2]));
                continue;
            }
            else if(a < 2 && b == n){
                out.println("0");
                continue;
            }
            out.println(Integer.toString(prefix[a - 2] + prefixReverse[b]));
        }
        out.close();
    }
}
