import java.io.*;
import java.util.*;
public class mergeSort {
    static int n;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        System.out.println("Enter an **ahem ahem** REASONABLE number n...");
        n = Integer.parseInt(in.readLine());
        System.out.println("Enter n numbers (in a reasonable range), one in each line...");
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(in.readLine());
        }
        in.close();
        int[] ans = mS(a).clone();
        for(int i: ans){
            out.println(i);
        }
        out.close();
    }
    static int[] mS(int[] arr){
        if(arr.length == 1){
            return arr;
        }
        int[] l = new int[arr.length/2], r = new int[arr.length - arr.length/2];
        System.arraycopy(arr, 0, l, 0, arr.length / 2);
        if (arr.length - arr.length / 2 >= 0)
            System.arraycopy(arr, arr.length / 2, r, 0, arr.length - arr.length / 2);
        int[] rL = mS(l).clone();
        int[] rR = mS(r).clone();
        int[] ret = new int[rL.length + rR.length];
        int lP = 0, rP = 0;
        for(int i = 0; i < ret.length; i++){
            if(lP == rL.length){
                ret[i] = rR[rP];
            }
            else if(rP == rR.length){
                ret[i] = rL[lP];
            }
            else{
                if(rR[rP] <= rL[lP]){
                    ret[i] = rR[rP];
                    rP++;
                }
                else{
                    ret[i] = rL[lP];
                    lP++;
                }
            }
        }
        return ret;
    }
}
