import java.io.*;
import java.util.*;
public class keyboard {
    static int n, m, x, q;
    static char[][] keyboard;
    static Map<Character, ArrayList<point>> map;
    static String word;
    static boolean[] preComp = new boolean[26];
    static boolean check(char currChar){
        for(point i: map.get(currChar)){
            for(point j: map.get('S')){
                if(distance(i, j) <= x){
                    return true;
                }
            }
        }
        return false;
    }
    static float distance(point a, point b){
        return (float)Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        keyboard = new char[n][m];
        map = new HashMap<>();
        for(char i = 'a'; i <= 'z'; i++){
            map.put(i, new ArrayList<>());
        }
        map.put('S', new ArrayList<>());
        for(int i = 0; i < n; i++){
            String row = in.readLine();
            keyboard[i] = row.toCharArray();
            for(int j = 0; j < m; j++){
                map.get(keyboard[i][j]).add(new point(j, i));
            }
        }
        q = Integer.parseInt(in.readLine());
        word = in.readLine();
        in.close();

        for(int i = 97; i <= 122; i++){
            preComp[i - 97] = check((char)i);
        }
        int ans = 0;
        for(int i = 0; i < q; i++){
            char curr = word.charAt(i);
            if(curr >= 65 && curr <= 90){
                if(map.get((char)(curr + 32)).size() == 0){
                    System.out.println(-1);
                    return;
                }
                if(map.get('S').size() == 0){
                    System.out.println(-1);
                    return;
                }
                if(!preComp[curr - 65]) {
                    ans++;
                }
            }
            else{
                if(map.get(curr).size() == 0){
                    System.out.print(-1);
                    return;
                }
            }
        }
        System.out.println(ans);
    }
}

class point{
    int x, y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}