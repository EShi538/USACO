import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    private static void solve(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[][] space = new char[n][n];
        for(int i = 0; i < n; i++){
            String row = in.next();
            char[] chArr = row.toCharArray();
            space[i] = chArr;
        }
        in.close();

        Queue<Integer> qx = new LinkedList<Integer>();
        Queue<Integer> qy = new LinkedList<Integer>();
        int[] Dx = {0, 1, 0, -1};
        int[] Dy = {-1, 0, 1, 0};

        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(space[i][j] == '*'){
                    cnt++;
                    //BFS
                    qx.add(j);
                    qy.add(i);
                    while(!qx.isEmpty() && !qy.isEmpty()){
                        int currX = qx.poll();
                        int currY = qy.poll();
                        for(int e = 0; e < 4; e++){
                            int nX = currX + Dx[e];
                            int nY = currY + Dy[e];
                            if(nX >= 0 && nX < n && nY >= 0 && nY < n && space[nY][nX] == '*'){
                                qx.add(nX);
                                qy.add(nY);
                                space[nY][nX] = '.';
                            }
                        }
                    }
                }
            }
        }
        System.out.println(cnt);
    }
    public static void main(String[] args) throws Exception {
        solve();
    }
}
