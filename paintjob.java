import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int A = in.nextInt(); //bessie's starting color
    static int B = in.nextInt(); //desired color
    static int N = in.nextInt(); //number of usable colors
    static int P = in.nextInt(); //the mod factor
    static int[] possCol = new int[N]; //array of usable colors
    static boolean[] visited = new boolean[P];

    public static int BFS(int A, int B){
        Queue<paint> q = new LinkedList<paint>();
        q.add(new paint(A % P, 0));
        while(!q.isEmpty()){
            paint curr = q.poll();
            if(curr.color == B){
                return curr.depth;
            }
            for(int i = 0; i < N; i++){
                int nColor = curr.color * possCol[i];
                if(visited[nColor % P] == false){
                    visited[nColor % P] = true;
                    q.add(new paint(nColor % P, curr.depth + 1));
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < N; i++){
            possCol[i] = in.nextInt();
        }
        System.out.print(BFS(A, B));
        in.close();
    }
}

class paint{
    int color;
    int depth;
    public paint(int color, int depth){
        this.color = color;
        this.depth = depth;
    }
}