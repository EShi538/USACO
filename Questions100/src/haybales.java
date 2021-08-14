import java.io.*;
import java.util.*;
public class haybales {
    static int n, q;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        FileWriter out = new FileWriter("haybales.out");
        Arrays.sort(arr);
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            out.write(Integer.toString(find(a, b)) + "\n");
        }
        in.close();
        out.close();
    }
    static int find(int a, int b){
        int l = Arrays.binarySearch(arr, a), r = Arrays.binarySearch(arr, b);
        if(l < 0){
            l = -(l + 1);
        }
        if(r < 0){
            r = -(r + 1) - 1;
        }
        return r - l + 1;
    }
}
