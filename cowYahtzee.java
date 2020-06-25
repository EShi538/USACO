import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt(); //# rolls
    static int s = in.nextInt(); //# sides
    static int e = in.nextInt(); //# expressions
    static int[][] origHist = new int[e][s + 1];
    static String[] exSpl = new String[s + 1];
    static int cnt = 0;
    static int cnt1 = 0;
    public static void gen(int ind, int[] hist){
        if(ind == n){
            if(good(hist)){
                cnt++;
            }
            return;
        }
        for(int i = 1; i <= s; i++){
            hist[i]++;
            gen(ind + 1, hist);
            hist[i]--;
        }
        return;
    }
    public static boolean good(int[] hist){
        for(int i = 0; i < e; i++){
            int[] currHist = origHist[i];
            boolean good = true;
            for(int j = 0; j < currHist.length; j++){
                if(hist[j] < currHist[j]){
                    good = false;
                    break;
                }
            }
            if(good == true){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        input();
        gen(0, new int[s + 1]);
        System.out.println(cnt);
    }
    public static int[][] input(){
        for(int i = 0; i < e; i++){
            String ex = in.next();
            boolean mult = false;
            for(int e = 0; e < ex.length(); e++){
                if(ex.charAt(e) == '+'){
                    mult = true;
                    break;
                }
            }
            if(mult == true){
                exSpl = ex.split("\\+");
                for(int j = 0; j < exSpl.length; j++){
                    String[] nums = exSpl[j].split("x");
                    origHist[i][Integer.parseInt(nums[1])] = Integer.parseInt(nums[0]);
                }
            }
            else{
                String[] nums = ex.split("x");
                origHist[i][Integer.parseInt(nums[1])] = Integer.parseInt(nums[0]);
            }
        }
        return origHist;
    }
}
