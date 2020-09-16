import java.io.*;
import java.util.*;
public class diamond {
    static int n, k;
    static int[] d;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter out = new PrintWriter(new File("diamond.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = new int[n];
        for(int i = 0; i < n; i++){
            d[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        Arrays.sort(d);
        int ans = -1;
        int maxlSeg = -1;
        for(int i = 0; i < n; i++){
            int r = Arrays.binarySearch(d, i, n, d[i] + k);
            int rSeg;
            if(r >= 0) {
                int p = r;
                while(p < n && d[p] == d[i] + k) {
                    p++;
                }
                r = p - 1;
                rSeg = r - i + 1;
            }
            else{
                r = -(r + 1);
                rSeg = r - i;
            }
            int l = (i != 0) ? Arrays.binarySearch(d, 0, i, d[i - 1] - k) : 0;
            int lSeg;
            if(i != 0) {
                if (l >= 0) {
                    int p = l;
                    boolean looped = false;
                    while (p >= 0 && d[p] == d[i - 1] - k) {
                        looped = true;
                        p--;
                    }
                    l = (looped) ? p + 1 : p;
                } else {
                    l = -(l + 1);
                }
            }
            lSeg = i - l;
            maxlSeg = Math.max(lSeg, maxlSeg);
            ans = Math.max(ans, rSeg + maxlSeg);
        }
        out.println(ans);
        out.close();
    }
}