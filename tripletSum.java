import java.io.*;
import java.util.*;
public class tripletSum {
    static int n, x;
    static num[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new num[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = new num(Integer.parseInt(st.nextToken()), i + 1);
        }
        in.close();
        Arrays.sort(arr, new sort());
        for(int i = 0; i < n; i++){
            int target = x - arr[i].num;
            int[] output = tp(target, i);
            if(output[0] != -1){
                System.out.println((arr[i].index) + " " + (arr[output[0]].index) + " " + (arr[output[1]].index));
                return;
            }
        }
        System.out.println("IMPOSSIBLE");
    }
    static int[] tp(int target, int badIndex){
        int l = 0, r = n - 1;
        int[] ans = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        if(l == badIndex){
            l++;
        }
        if(r == badIndex){
            r--;
        }
        while(l < r){
            int sum = arr[l].num + arr[r].num;
            if(sum > target){
                r--;
            }
            else if(sum < target){
                l++;
            }
            else{
                ans[0] = l;
                ans[1] = r;
                return ans;
            }
            if(l == badIndex){
                l++;
            }
            if(r == badIndex){
                r--;
            }
        }
        return ans;
    }
}

class num{
    int num, index;
    public num(int num, int index){
        this.num = num;
        this.index = index;
    }
}
class sort implements Comparator<num>{
    public int compare(num a, num b){
        return a.num - b.num;
    }
}