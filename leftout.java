import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);   
    static int n = in.nextInt();
    static boolean[][] matrix = new boolean[n][n];

    //reverse functions
    public static void reverseRow(int x){
        for(int i = 0; i < n; i++){
            if(matrix[x][i] == false){
                matrix[x][i] = true;
            }
            else{
                matrix[x][i] = false;
            }
        }
        return;
    }

    public static void reverseColumn(int x){
        for(int i = 0; i < n; i++){
            if(matrix[i][x] == false){
                matrix[i][x] = true;
            }
            else{
                matrix[i][x] = false;
            }
        }
        return;
    }

    //find the sum of trues in middle section
    public static int sumOfTrue(){
        int cnt = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == true){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    //check if an entire row is a true
    public static boolean rowFilled(int x){
        boolean good = true;
        for(int i = 1; i < n; i++){
            if(matrix[x][i] == false){
                good = false;
                break;
            }
        }
        if(good == true){
            return true;
        }
        return false;
    }

    //check if entire column is true
    public static boolean columnFilled(int x){
        boolean good = true;
        for(int i = 1; i < n; i++){
            if(matrix[i][x] == false){
                good = false;
                break;
            }
        }
        if(good == true){
            return true;
        }
        return false;
    }

    //true = right; false = left
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < n; i++){
            String row = in.next();
            char[] chArr = row.toCharArray();
            boolean[] rowArr = new boolean[n];
            for(int j = 0; j < chArr.length; j++){
                if(chArr[j] == 'R'){
                    rowArr[j] = true;
                }
                else{
                    rowArr[j] = false;
                }
            }
            matrix[i] = rowArr;
        } 
        in.close();

        //standardize
        //reverse top row
        for(int i = 0; i < n; i++){
            if(matrix[0][i] == true){
                reverseColumn(i);
            }
        }
        //reverse left column
        for(int i = 0; i < n; i++){
            if(matrix[i][0] == true){
                reverseRow(i);
            }
        }
        //find number of rights inside the "middle" section
        int numTrue = sumOfTrue();
        
        //cases
        //if everything in the middle section is true
        if(numTrue == Math.pow(n - 1, 2)){
            System.out.println(1 + " " + 1);
            return;
        }
        //if a row is all correct
        for(int i = 1; i < n; i++){
            if(numTrue == n - 1 && rowFilled(i)){
                System.out.println((i + 1) + " " + 1);
                return;
            }
        }
        //if a column is all correct
        for(int i = 1; i < n; i++){
            if(numTrue == n - 1 && columnFilled(i)){
                System.out.println(1 + " " + (i + 1));
                return;
            }
        }
        //if all but one in the middle is correcct
        if(numTrue == 1){
            for(int i = 1; i < n; i++){
                for(int j = 1; j < n; j++){
                    if(matrix[i][j] == true){
                        System.out.println((i + 1) + " " + (j + 1));
                        return;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
