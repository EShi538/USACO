import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    private static void solve(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean[][] matrix = new boolean[3000][3000];
        String instruction = in.next();
        char[] directions = instruction.toCharArray();
        int penx = 1500;
        int peny = 1500; 
        matrix[peny][penx] = true;

        //setting up matrix
        for(int i = 0; i < directions.length; i++){
            if(directions[i] == 'N'){
                peny--;
                matrix[peny][penx] = true;
                peny--;
                matrix[peny][penx] = true;
            }
            else if(directions[i] == 'S'){
                peny++;
                matrix[peny][penx] = true;
                peny++;
                matrix[peny][penx] = true;
            }
            else if(directions[i] == 'E'){
                penx++;
                matrix[peny][penx] = true;
                penx++;
                matrix[peny][penx] = true;
            }
            else {
                penx --;
                matrix[peny][penx] = true;
                penx --;
                matrix[peny][penx] = true;
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int cnt = 0;

        //BFS floodfill
        for(int y = 0; y < 3000; y++){
            for(int x = 0; x < 3000; x++){
                if(matrix[y][x] == false){
                    cnt++;
                    Queue<Integer> qx = new LinkedList<Integer>();
                    Queue<Integer> qy = new LinkedList<Integer>();
                    qx.add(x);
                    qy.add(y);
                    matrix[y][x] = true;
                    while(!qx.isEmpty()){
                        int currx = qx.poll();
                        int curry = qy.poll();
                        for(int i = 0; i < 4; i++){
                            int nx = currx + dx[i];
                            int ny = curry + dy[i];
                            if(nx >= 0 && nx < 3000 && ny >= 0 && ny < 3000 && matrix[ny][nx] == false){
                                matrix[ny][nx] = true;
                                qx.add(nx);
                                qy.add(ny);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(cnt - 1);
        in.close();
    }
    public static void main(String[] args) throws Exception {
        solve();
    }
}
