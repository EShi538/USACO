import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class App {
    static Set<Integer> poss = new HashSet<Integer>();
    static boolean[][][] visited = new boolean[21][21][21];
    static Scanner in = new Scanner(System.in);
    static int A = in.nextInt();
    static int B = in.nextInt();
    static int C = in.nextInt();
    public static void DFS(config curr){
        visited[curr.A][curr.B][curr.C] = true;
        if(curr.A == 0) {
            poss.add(curr.C);
        }
        //A -> B
        int[] pours = pour(curr.A, curr.B, A, B);
        config check = new config(pours[0], pours[1], curr.C); 
        if(visited[check.A][check.B][check.C] == false){
            DFS(check);
        }   
        
        //A -> C
        pours = pour(curr.A, curr.C, A, C);
        check = new config(pours[0], curr.B, pours[1]); 
        if(visited[check.A][check.B][check.C] == false){
            DFS(check);
        }   

        //B -> A
        pours = pour(curr.B, curr.A, B, A);
        check = new config(pours[1], pours[0], curr.C); 
        if(visited[check.A][check.B][check.C] == false){
            DFS(check);
        }

        //B -> C
        pours = pour(curr.B, curr.C, B, C);
        check = new config(curr.A, pours[0], pours[1]); 
        if(visited[check.A][check.B][check.C] == false){
            DFS(check);
        }

        //C -> A
        pours = pour(curr.C, curr.A, C, A);
        check = new config(pours[1], curr.B , pours[0]); 
        if(visited[check.A][check.B][check.C] == false){
            DFS(check);
        }

        //C -> B
        pours = pour(curr.C, curr.B, C, B);
        check = new config(curr.A, pours[1], pours[0]); 
        if(visited[check.A][check.B][check.C] == false){
            DFS(check);
        }

        return;
    }
    public static int[] pour(int a, int b, int A, int B){
        //a & b = curr amount
        //A & B = capacity
        //pour a->b 
        int[] a_b = new int[2];
        int needed = B - b;
        if(a < needed){
            a_b[0] = 0;
            a_b[1] = b + a;
        }
        else if(a >= needed){
            a_b[0] = a - needed;
            a_b[1] = B;
        }
        return a_b;
        
    }
     public static void main(String[] args) throws Exception {
        DFS(new config(0, 0, C));
        for(int i: poss){
            System.out.print(i + " ");
        }
    }
}
class config{
    int A;
    int B;
    int C;
    public config(int A, int B, int C){
        this.A = A;
        this.B = B;
        this.C = C;
    }
}