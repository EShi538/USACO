import java.io.*;
public class hps {
    static int n;
    static char[] a;
    static int[] preH, preP, preS;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("hps.in"));
        n = Integer.parseInt(in.readLine());
        a = new char[n];
        for(int i = 0; i < n ; i++){
            a[i] = in.readLine().charAt(0);
        }
        in.close();
        preH = new int[n]; preP = new int[n]; preS = new int[n];
        for(int i = 0; i < n; i++){
            if(a[i] != 'H'){
                preH[i] = (i == 0) ? 0: preH[i - 1];
                continue;
            }
            preH[i] = (i == 0) ? 1 : preH[i - 1] + 1;
        }
        for(int i = 0; i < n; i++){
            if(a[i] != 'P'){
                preP[i] = (i == 0) ? 0: preP[i - 1];
                continue;
            }
            preP[i] = (i == 0) ? 1 : preP[i - 1] + 1;
        }
        for(int i = 0; i < n; i++){
            if(a[i] != 'S'){
                preS[i] = (i == 0) ? 0: preS[i - 1];
                continue;
            }
            preS[i] = (i == 0) ? 1 : preS[i - 1] + 1;
        }
        int ans = Math.max(preH[n - 1], Math.max(preP[n - 1], preS[n - 1]));
        for(int i = 0; i < n - 1; i++){
            int tot = Math.max(preH[i], Math.max(preP[i], preS[i]));
            tot += Math.max(preH[n - 1] - preH[i], Math.max(preP[n - 1] - preP[i], preS[n - 1] - preS[i]));
            ans = Math.max(ans, tot);
        }
        FileWriter out = new FileWriter("hps.out");
        out.write(Integer.toString(ans));
        out.close();
    }
}
