import java.io.*;
import java.util.*;
public class maxProducts {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int e = 0; e < t; e++){
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            List<Integer> negs = new ArrayList<>(), pos = new ArrayList<>();
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(st.nextToken());
                if(a[i] < 0){
                    negs.add(a[i]);
                }
                else{
                    pos.add(a[i]);
                }
            }
            Collections.sort(negs);
            Collections.sort(pos);
            long ans = Long.MIN_VALUE;
            for(int i = 0; i <= 5; i++){
                long prod = 1;
                boolean bad = false;
                if(i % 2 == 1){
                    for(int j = negs.size() - 1; j >= negs.size() - i; j--){
                        if(j == -1){
                            bad = true;
                            break;
                        }
                        prod *= negs.get(j);
                    }
                }
                else{
                    for(int j = 0; j < i; j++){
                        if(j == negs.size()){
                            bad = true;
                            break;
                        }
                        prod *= negs.get(j);
                    }
                }
                if(bad) {
                    continue;
                }
                for(int j = pos.size() - 1; j >= pos.size() - (5 - i); j--){
                    if(j == -1){
                        bad = true;
                        break;
                    }
                    prod *= pos.get(j);
                }
                if(bad){
                    continue;
                }
                ans = Math.max(prod, ans);
            }
            out.println(ans);
        }
        out.close();
    }
}
