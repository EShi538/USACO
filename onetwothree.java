import java.io.*;
import java.util.*;
public class onetwothree{
    static long k;
    static int a, b;
    static int[][] A, B;
    static Map<String, String> map = new HashMap<>();
    static long[] solve(String start){
        TreeSet<str> states = new TreeSet<>(new sort());
        List<Integer> APS = new ArrayList<>();
        List<Integer> BPS = new ArrayList<>();
        int moves = 0;
        String currState = start;
        int cycleInd = -1;
        while(true){
            if(moves == k) {
                break;
            }
            if(binSearch(currState, states) >= 0) {
                cycleInd = binSearch(currState, states);
                break;
            }
            states.add(new str(currState, states.size()));
            if(APS.size() == 0) {
                if(currState.charAt(0) == currState.charAt(1)){
                    APS.add(0); BPS.add(0);
                }
                else {
                    APS.add((Awins(currState.charAt(0) - 48, currState.charAt(1) - 48)) ? 1 : 0);
                    BPS.add((Awins(currState.charAt(0) - 48, currState.charAt(1) - 48)) ? 0 : 1);
                }
            }
            else {
                if(currState.charAt(0) == currState.charAt(1)){
                    APS.add(APS.get(APS.size() - 1)); BPS.add(BPS.get(BPS.size() - 1));
                }
                else {
                    APS.add((Awins(currState.charAt(0) - 48, currState.charAt(1) - 48)) ? APS.get(APS.size() - 1) + 1 : APS.get(APS.size() - 1));
                    BPS.add((Awins(currState.charAt(0) - 48, currState.charAt(1) - 48)) ? BPS.get(BPS.size() - 1) : BPS.get(BPS.size() - 1) + 1);
                }
            }
            currState = map.get(currState);
            moves++;
        }
        long[] ans = new long[2];
        if (cycleInd >= 0) {
            int ApointsBeforeCycle = (cycleInd == 0) ? 0 : APS.get(cycleInd - 1);
            int ApointsAfterCycle = APS.get(APS.size() - 1) - ApointsBeforeCycle;
            int BpointsBeforeCycle = (cycleInd == 0) ? 0 : BPS.get(cycleInd - 1);
            int BpointsAfterCycle = BPS.get(BPS.size() - 1) - BpointsBeforeCycle;
            ans[0] += ApointsBeforeCycle;
            ans[1] += BpointsBeforeCycle;
            int cycleLength = states.size() - (cycleInd);
            k -= BPS.size() - cycleLength;
            long times = k / cycleLength;
            ans[0] += ApointsAfterCycle * (times);
            ans[1] += BpointsAfterCycle * (times);
            long remainder = k % cycleLength;
            if (cycleInd == 0) {
                if (remainder != 0) {
                    ans[0] += APS.get((int)remainder - 1);
                    ans[1] += BPS.get((int)remainder - 1);
                }
            } else {
                ans[0] += APS.get((int)(cycleInd + remainder - 1)) - APS.get(cycleInd - 1);
                ans[1] += BPS.get((int)(cycleInd + remainder - 1)) - BPS.get(cycleInd - 1);
            }
        }
        else{
            ans[0] = APS.get(APS.size() - 1);
            ans[1] = BPS.get(BPS.size() - 1);
        }
        return ans;
    }
    static boolean Awins(int A, int B){
        return (A == 3 && B == 2) || (A == 2 && B == 1) || (A == 1 && B == 3);
    }
    static int binSearch(String target, TreeSet<str> states){
        List<str> l = new ArrayList<>(states);
        int lb = 0, ub= states.size();
        boolean found = false;
        while(lb != ub){
            int m = (lb + ub)/2;
            if(l.get(m).word.compareTo(target) < 0)
                lb = m + 1;
            else if(l.get(m).word.compareTo(target) > 0)
                ub = m;
            else{
                found = true;
                ub = m;
                lb = m;
            }
        }
        if(found) {
            return l.get(lb).index;
        }
        return -(lb) - 1;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        k = Long.parseLong(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        A = new int[3][3]; B = new int[3][3];
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(in.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int a3 = Integer.parseInt(st.nextToken());
            A[i][0] = a1; A[i][1] = a2; A[i][2] = a3;
        }
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(in.readLine());
            int b1 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());
            int b3 = Integer.parseInt(st.nextToken());
            B[i][0] = b1; B[i][1] = b2; B[i][2] = b3;
        }
        in.close();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                char a = (char)(A[i][j] + 48);
                char b = (char)(B[i][j] + 48);
                String ab = String.valueOf(a) + b;
                String ij = String.valueOf(i + 1) + (j + 1);
                map.put(ij, ab);
            }
        }
        long[] ans = solve(String.valueOf(a) + b);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
class str{
    String word;
    int index;
    public str(String word, int index){
        this.word = word;
        this.index = index;
    }
}
class sort implements Comparator<str>{
    public int compare(str a, str b){
        return a.word.compareTo(b.word);
    }
}