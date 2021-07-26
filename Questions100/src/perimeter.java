import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class perimeter {
    static int n = 0;
    static char[][] icecream = new char[1][1];
    static int area = 0;
    static int perimeter = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    public static void DFS(int x, int y){
        Stack<point> st = new Stack<point>();
        st.push(new point(x, y));
        icecream[y][x] = '$';
        area++;
        perimeter = perimeter + onBorder(x, y);
        while(!st.isEmpty()){
            point curr = st.pop();
            for(int i = 0; i < 4; i++){
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(inBound(nx, ny) && icecream[ny][nx] == '#'){
                    st.push(new point(nx, ny));
                    icecream[ny][nx] = '$';
                    area++;
                    perimeter = perimeter + onBorder(nx, ny);
                }
            }
        }
        return;
    }

    public static boolean inBound(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static int onBorder(int x, int y){
        int cnt = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if((inBound(nx, ny) && icecream[ny][nx] == '.')){
                cnt++;
            }
            else if(inBound(nx, ny)== false){
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("perimeter.in");
        BufferedReader in = new BufferedReader(reader);
        n = Integer.parseInt(in.readLine());
        icecream = new char[n][n];
        for(int i = 0; i < n; i++){
            String row = in.readLine();
            icecream[i] = row.toCharArray();
        }
        in.close();

        int maxA = -1;
        List<blob> blobs = new ArrayList<blob>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(icecream[i][j] == '#'){
                    DFS(j, i);
                    if(area > maxA){
                        maxA = area;
                    }
                    blobs.add(new blob(perimeter, area));
                    area = 0;
                    perimeter = 0;
                }
            }
        }
        int minP = Integer.MAX_VALUE;
        for(int i = 0; i < blobs.size(); i++){
            if(blobs.get(i).area == maxA){
                if(blobs.get(i).per < minP){
                    minP = blobs.get(i).per;
                }
            }
        }
        File out = new File("perimeter.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(maxA) + " " + Integer.toString(minP));
        writer.close();
    }
}

class blob{
    int per;
    int area;
    public blob(int per, int area){
        this.per = per;
        this.area = area;
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