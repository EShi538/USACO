import java.io.*;
import java.util.*;
public class books {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[] books = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            books[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        int[] booksPS = new int[n];
        for(int i = 0; i < n; i++){
            if(i == 0){
                booksPS[i] = books[i];
                continue;
            }
            booksPS[i] = booksPS[i - 1] + books[i];
        }
        int p1 = 0;
        int p2 = 0;
        int ans = 0;
        while(p1 < n && p2 < n){
            //System.out.print("p1: " + p1 + " p2: " + p2);
            int sum = -1;
            if(p1 == 0){
                sum = booksPS[p2];
            }
            else{
                sum = booksPS[p2] - booksPS[p1 - 1];
            }
            //System.out.println(" sum: " + sum);
            if(sum > t){
                p1++;
            }
            else{
                if(p2 - p1 + 1 > ans){
                    ans = p2 - p1 + 1;
                }
                p2++;
            }
        }
        System.out.println(ans);
    }
}