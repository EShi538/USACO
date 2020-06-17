import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int W = in.nextInt();
    static int H = in.nextInt();
    static int[][] field = new int[H][W];
    static point st1 = new point(0, 0, 0);
    static int cnt = 0;
    static point st2 = new point(0, 0, 0);
    static int check = 0;
    public static void BFS (){
        Queue<point> q = new LinkedList<point>();
        q.add(st1);
        field[st1.y][st1.x] = st1.depth;
        while(!q.isEmpty()){
            point curr = q.poll();
            int j = 1;
            while(curr.x + j < W && (field[curr.y][curr.x + j] == -1 || field[curr.y][curr.x + j] == -3 || field[curr.y][curr.x + j] == curr.depth)){
                q.add(new point(curr.x + j, curr.y, curr.depth + 1));
                field[curr.y][curr.x + j] = curr.depth;
                j++;
            }
            j = 1;
            while(curr.x - j >= 0 && (field[curr.y][curr.x - j] == -1 || field[curr.y][curr.x - j] == -3 || field[curr.y][curr.x - j] == curr.depth)){
                q.add(new point(curr.x - j, curr.y, curr.depth + 1));
                field[curr.y][curr.x - j] = curr.depth;
                j++;
            }
            j = 1;
            while(curr.y + j < H && (field[curr.y + j][curr.x] == -1 || field[curr.y + j][curr.x] == -3 || field[curr.y + j][curr.x] == curr.depth)){
                q.add(new point(curr.x, curr.y + j, curr.depth + 1));
                field[curr.y + j][curr.x] = curr.depth;
                j++;
            }
            j = 1;
            while(curr.y - j >= 0 && (field[curr.y - j][curr.x] == -1 || field[curr.y - j][curr.x] == -3 || field[curr.y - j][curr.x] == curr.depth)){
                q.add(new point(curr.x, curr.y - j, curr.depth + 1));
                field[curr.y - j][curr.x] = curr.depth;
                j++;
            }
            // if(check < 3){
            //     for(int i = 0; i < H; i++){
            //         for(int r = 0; r < W; r++){
            //             System.out.print(field[i][r] + " ");
            //         }
            //         System.out.println();
            //     }
            //     System.out.println();
            // }
            // check++;
        }
    }
    public static void main(String[] args) throws Exception {
        int cnt = 0;
        for(int i = 0; i < H; i++){
            String row = in.next();
            char[] chArr = row.toCharArray();
            int[] actual = new int[chArr.length];
            for(int j = 0; j < W; j++){
                if(chArr[j] == 'C' && cnt == 0){
                    st1 = new point(j, i, 0);
                    actual[j] = -3;
                    cnt++;
                }
                if(chArr[j] == 'C' && cnt == 1){
                    actual[j] = -3;
                    st2 = new point(j, i, 0);
                }
                if(chArr[j] == '.'){
                    actual[j] = -1;
                }
                else if(chArr[j] == '*'){
                    actual[j] = -2;
                }
            }
            field[i] = actual;
        }
        BFS();
        System.out.print(field[st2.y][st2.x]);
    }
}

class point{
    int x; 
    int y;
    int depth;
    public point(int x, int y, int depth){
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}