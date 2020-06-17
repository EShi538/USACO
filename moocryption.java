import java.util.Scanner;
public class App {
    public static boolean inBound(int x, int y, int x1, int y1, int h, int l){
        return x >= 0 && x <= l - 1 && y >= 0 && y <= h - 1 && x1 >= 0 && x1 <= l - 1 && y1 >= 0 && y1 <= h - 1;
    }
    public static void main(String[] args) throws Exception {
        // //input
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int l = in.nextInt();
        char[][] matrix = new char[h][l];
        for(int i = 0; i < h; i++){
            String row = in.next();
            char[] rowArr = row.toCharArray();
            matrix[i] = rowArr;
        }
        in.close();

        // //direction arrays;
        int[] xD = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] yD = {-1, -1, 0, 1,  1, 1, 0, -1};

        int max = Integer.MIN_VALUE;
        //brute force time!!! (i -> M; j -> O)
        for(char i = 'A'; i <= 'Z'; i++){
            if(i == 'M'){
                continue;
            }
            for(char j = 'A'; j <= 'Z'; j++){
                if(j == 'O'){
                    continue;
                }
                if(i == j){
                    continue;
                }
                int cnt = 0;
                //loop through possible positions in the matrix
                for(int e = 0; e < h; e++){
                    for(int k = 0; k < l; k++){
                        if(matrix[e][k] == i){
                            for(int x = 0; x < 8; x++){
                                int Nx = k + xD[x];
                                int Ny = e + yD[x];
                                int tNx = k + 2 * xD[x];
                                int tNy = e + 2 * yD[x];
                                if(inBound(Nx, Ny, tNx, tNy, h, l) && matrix[Ny][Nx] == j && matrix[tNy][tNx] == j){
                                    cnt++;
                                }
                            }
                        }
                    }
                }
                if(cnt > max){
                    max = cnt;
                }
            }
        }

        //output
        System.out.print(max);
    }
}
