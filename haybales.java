import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class haybales {
    public static void main(String[] args) throws Exception {
        // File file = new File("haybales.in");
        // Scanner in = new Scanner(file);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        boolean[] check = new boolean[1000001];
        for(int i = 0; i < n; i++){
            int pos = in.nextInt();
            check[pos] = true;
        } 
        List<query> qs = new ArrayList<query>();
        for(int i = 0; i < q; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            qs.add(new query(a, b));
        }
        in.close();

        int[] PS = new int[1000001];
        if(check[0] == true){
            PS[0] = 1;
        }
        else{
            PS[0] = 0;
        }
        for(int i = 1; i <= 100000; i++){
            if(check[i] == true){
                PS[i] = PS[i - 1] + 1;
            }
            else{
                PS[i] = PS[i - 1];
            }
        }

        //File file1 = new File("haybales.out");
        //FileWriter writer = new FileWriter(file1);
        for(int i = 0; i < q; i++){
            int a = qs.get(i).a;
            int b = qs.get(i).b;
            int num = 0;
            if(a == 0){
                num = PS[b];
            }
            else{
                num = PS[b] - PS[a - 1];
            }
            //writer.write(Integer.toString(num) + "\n");
            System.out.println(num);
        }
        //writer.close();
    }
}

class query{
    int a;
    int b;
    public query (int a, int b){
        this.a = a;
        this.b = b;
    }
}