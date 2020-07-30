import java.io.*;
import java.util.*;
public class crazy {
    static boolean[][] visited;
    static int cnt = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static void floodfill(int x, int y, int[][] matrix){
        Queue<point> st = new LinkedList<point>();
        visited[y][x] = true;
        st.add(new point(x, y));
        while(!st.isEmpty()){
            point curr = st.poll();
            if(matrix[curr.y][curr.x] == 1){
                cnt++;
            }
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (inBound(nx, ny) && !visited[ny][nx] && matrix[ny][nx] != 2) {
                    st.add(new point(nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }
    }
    static boolean inBound(int x, int y){
        return x >= 0 && x < 2010 && y >= 0 && y < 2010;
    }
    public static void main(String[] args) throws Exception{
        //input
        FileReader reader = new FileReader("crazy.in");
        BufferedReader in = new BufferedReader(reader);
        File out = new File("crazy.out");
        PrintWriter pw = new PrintWriter(out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[2010][2010];
        visited = new boolean[2010][2010];
        Map<Integer, Integer> compressY = new HashMap<Integer, Integer>();
        Map<Integer, Integer> compressX = new HashMap<Integer, Integer>();
        Set<Integer> xs = new TreeSet<>();
        Set<Integer> ys = new TreeSet<>();
        List<fence> fences = new ArrayList<>();
        List<point> cows = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            xs.add(x1); xs.add(x2);
            ys.add(y1); ys.add(y2);
            fences.add(new fence(new point(x1, y1), new point(x2, y2)));
        }
        for(int i = 0; i < c; i++){
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            xs.add(x1); ys.add(y1);
            cows.add(new point(x1, y1));
        }
        in.close();

        //compress
        int comp = 0;
        for(int i: xs){
            compressX.put(i, comp);
            comp += 2;
        }
        comp = 0;
        for(int i: ys){
            compressY.put(i, comp);
            comp += 2;
        }
        for(fence i: fences){
            i.a.x = compressX.get(i.a.x); i.a.y = compressY.get(i.a.y);
            i.b.x = compressX.get(i.b.x); i.b.y = compressY.get(i.b.y);
        }
        for(point i: cows){
            i.x = compressX.get(i.x);
            i.y = compressY.get(i.y);
        }

        //draw (0 -> nothing; 1 -> cow; 2 -> fence)
        for(point i: cows){
            matrix[i.y][i.x] = 1;
        }
        for(fence i: fences){
            int start = 0;
            int end = 0;
            if(i.a.x == i.b.x){
                if(i.a.y < i.b.y){
                    start = i.a.y; end = i.b.y;
                }
                else{
                    start = i.b.y; end = i.a.y;
                }
                for(int j = start; j <= end; j++){
                    matrix[j][i.a.x] = 2;
                }
            }
            else{
                if(i.a.x < i.b.x){
                    start = i.a.x; end = i.b.x;
                }
                else{
                    start = i.b.x; end = i.a.x;
                }
                for(int j = start; j <= end; j++){
                    matrix[i.a.y][j] = 2;
                }
            }
        }
        //floodfill
        int ans = -1;
        for(int i = 0; i < 2010; i++){
            for(int j = 0; j < 2010; j++){
                if(!visited[i][j] && matrix[i][j] != 2){
                    floodfill(j, i, matrix);
                    ans = Math.max(ans, cnt);
                    cnt = 0;
                }
            }
        }
        pw.write(Integer.toString(ans));
        pw.close();
    }
}

class point{
    int x;
    int y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class fence{
    point a;
    point b;
    public fence(point a, point b){
        this.a = a;
        this.b = b;
    }
}