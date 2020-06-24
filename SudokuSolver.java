
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int[][] matrix = new int[9][9];
    static boolean solvable = false;
    public static void solve(int x, int y){
        if(x == 9){
            x = 0;
            y++;
        }
        if(x == 8 && y == 8){
            if(matrix[y][x] == 0){
                for(int i = 1; i <= 9; i++){
                    if(possible(x, y, i)){
                         matrix[y][x] = i;
                    }
                 }
            }
            solvable = true;
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }
        if(matrix[y][x] != 0){
            solve(x + 1, y);
        }
        else{
            for(int i = 1; i <= 9; i++){
               if(possible(x, y, i)){
                    matrix[y][x] = i;
                    solve(x + 1, y);
                    matrix[y][x] = 0;
               }
            }
            return;
        }
    }

    public static boolean possible(int x, int y, int n){
        for(int i = (x/3) * 3; i < (x/3 + 1) * 3; i++){
            for(int j = (y/3) * 3; j < (y/3 + 1) * 3; j++){
                if(matrix[j][i] == n){
                    return false;
                }
            }
        }
        for(int i = 0; i < 9; i++){
            if(matrix[i][x] == n || matrix[y][i] == n){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                matrix[i][j] = in.nextInt();
            }
        }
        solve(0, 0);
        if(solvable == false){
            System.out.println("NO SOLUTION");
        }
    }
}
