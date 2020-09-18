import java.io.*;
import java.util.*;
public class juryMarks {
    static int k, n;
    static int[] a, b, ps;
    static Set<Integer> s3 = new HashSet<>();
    static Set<Integer> s = new HashSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        a = new int[k];
        b = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < k; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            b[i] = Integer.parseInt(st.nextToken());
            s3.add(b[i]);
        }
        in.close();
        ps = new int[k];
        for(int i = 0; i < k; i++){
            ps[i] = (i == 0) ? a[i] : ps[i - 1] + a[i];
        }
        for(int j = 0; j < k; j++){
            s.add(b[0] - ps[j]);
        }
        int ans = 0;
        for(int i: s){
            Set<Integer> points = new HashSet<>();
            for(int j = 0; j < k; j++){
                if(s3.contains(i + ps[j])) {
                    points.add(i + ps[j]);
                }
            }
            if(points.size() == s3.size()){
                ans++;
            }
        }
        out.println(ans);
        out.close();
    }
}