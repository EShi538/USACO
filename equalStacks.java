import java.io.*;
import java.util.*;
public class equalStacks {
    static int n1, n2, n3;
    static Queue<Integer> l1 = new LinkedList<>(), l2 = new LinkedList<>(), l3 = new LinkedList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());
        n3 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int s1 = 0, s2 = 0, s3 = 0;
        for(int i = 0; i < n1; i++){
            int x = Integer.parseInt(st.nextToken());
            l1.add(x);
            s1 += x;
        }
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n2; i++){
            int x = Integer.parseInt(st.nextToken());
            l2.add(x);
            s2 += x;
        }
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n3; i++){
            int x = Integer.parseInt(st.nextToken());
            l3.add(x);
            s3 += x;
        }
        in.close();
        while(s1 != s3 || s2 != s3){
            if(s1 >= s2 && s2 >= s3){
                s1 -= l1.poll();
            }
            else if(s2 >= s1 && s1 >= s3){
                s2 -= l2.poll();
            }
            else{
                s3 -= l3.poll();
            }
        }
        out.println(s1);
        out.close();
    }
}
