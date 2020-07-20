import java.io.*;
import java.util.*;
public class mirrors {
    static int n;
    static int m;
    static char[][] mirrors;
    static int cnt = 0;
    static boolean done = false;
    static int dir;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static void sim(int x, int y, int dir){
        Stack<point> st = new Stack<>();
        st.push(new point(x, y, dir));
        while(!st.isEmpty()){
            point curr = st.pop();
            cnt++;
            int direct = 0;
            if(mirrors[curr.y][curr.x] == '/'){
                switch(curr.dir){
                    case 0: direct = 1; break;
                    case 1: direct = 0; break;
                    case 2: direct = 3; break;
                    case 3: direct = 2; break;
                }
            }
            else{
                switch(curr.dir){
                    case 0: direct = 3; break;
                    case 1: direct = 2; break;
                    case 2: direct = 1; break;
                    case 3: direct = 0; break;
                }
            }
            int nx = curr.x + dx[direct];
            int ny = curr.y + dy[direct];
            if(inBound(nx, ny)){
                st.add(new point(nx, ny, direct));
            }
            else if(!inBound(nx, ny)){
                done = true;
                return;
            }
        }
    }
    static boolean inBound(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mirrors = new char[n][m];
        for(int i = 0; i < n; i++){
            mirrors[i] = in.readLine().toCharArray();
        }
        in.close();

        int max = -1;
        for(int i = 0; i < m; i++){
            sim(i, 0, 2);
            max = Math.max(cnt, max);
            cnt = 0;
        }
        for(int i = 0; i < n; i++){
            sim(0, i, 1);
            max = Math.max(cnt, max);
            cnt = 0;
        }
        for(int i = 0; i < m; i++){
            sim(i, n - 1, 0);
            max = Math.max(cnt, max);
            cnt = 0;
        }
        for(int i = 0; i < n; i++){
            sim(m - 1, i, 3);
            max = Math.max(cnt, max);
            cnt = 0;
        }
        System.out.println(max);
    }
}

class point{
    int x;
    int y;
    int dir;
    public point(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}