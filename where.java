import java.io.*;
import java.util.*;
public class where {
    static int n;
    static char[][] matrix;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static List<PCL> pcls = new ArrayList<>();
    static void dfs(int x, int y, char regex, int maxX, int minX, int maxY, int minY){
        visited[y][x] = true;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i]; int ny = y + dy[i];
            if(inBound(nx, ny, maxX, minX, maxY, minY) && !visited[ny][nx] && matrix[ny][nx] == regex){
                dfs(nx, ny, regex, maxX, minX, maxY, minY);
            }
        }
    }
    static boolean inBound(int x, int y, int maxX, int minX, int maxY, int minY){
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
    //first rectangles encompasses second
    static boolean encompasses(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        return x1 <= x3 && x2 >= x4 && y1 <= y3 && y2 >= y4;
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("where.in");
        BufferedReader in = new BufferedReader(reader);
        File out = new File("where.out");
        PrintWriter writer = new PrintWriter(out);
        n = Integer.parseInt(in.readLine());
        matrix = new char[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            String row = in.readLine();
            matrix[i] = row.toCharArray();
        }
        in.close();

        int rectCnt = 0;
        for(int lowerX = 0; lowerX < n; lowerX++){
            for(int lowerY = 0; lowerY < n; lowerY++){
                for(int upperX = lowerX; upperX < n; upperX++){
                    for(int upperY = lowerY; upperY < n; upperY++){
                        if(upperX == lowerX && upperY == lowerY){
                            continue;
                        }
                        rectCnt++;
                        TreeSet<Character> characters = new TreeSet<>();
                        for(int i = lowerY; i <= upperY; i++){
                            for(int j = lowerX; j <= upperX; j++){
                                characters.add(matrix[i][j]);
                            }
                        }
                        if(characters.size() == 2){
                            char a = characters.first(); char b = characters.last();
                            int aCnt = 0, bCnt = 0;
                            visited = new boolean[n][n];
                            for(int i = lowerY; i <= upperY; i++){
                                for(int j = lowerX; j <= upperX; j++){
                                    if(!visited[i][j]){
                                        if(matrix[i][j] == b){
                                            bCnt++;
                                        }
                                        else{
                                            aCnt++;
                                        }
                                        dfs(j, i, matrix[i][j], upperX, lowerX, upperY, lowerY);
                                    }
                                }
                            }
                            if(aCnt == 1 && bCnt >= 2){
                                pcls.add(new PCL(lowerX, lowerY, upperX, upperY));
                            }
                            else if(bCnt == 1 && aCnt >= 2){
                                pcls.add(new PCL(lowerX, lowerY, upperX, upperY));
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < pcls.size(); i++){
            PCL curr = pcls.get(i);
            boolean bad = false;
            for (PCL pcl : pcls) {
                if (pcl.x1 == curr.x1 && pcl.x2 == curr.x2 && pcl.y1 == curr.y1 && pcl.y2 == curr.y2) {
                    continue;
                }
                if (encompasses(pcl.x1, pcl.y1, pcl.x2, pcl.y2, curr.x1, curr.y1, curr.x2, curr.y2)) {
                    bad = true;
                    break;
                }
            }
            if(!bad){
                ans++;
            }
        }
        writer.println(ans);
        writer.close();
    }
}

class PCL{
    int x1, y1, x2, y2;
    public PCL(int x1, int y1, int x2, int y2){
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
    }
}