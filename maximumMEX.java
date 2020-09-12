import java.io.*;
import java.util.*;
public class maximumMEX {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for(int j = 0; j < n; j++){
                a[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a);
            Map<Integer, Integer> occs = new HashMap<>();
            for(int j: a){
                if(!occs.containsKey(j)){
                    occs.put(j, 1);
                }
                else{
                    occs.put(j, occs.get(j) + 1);
                }
            }
            List<Integer> A = new ArrayList<>(), B = new ArrayList<>();
            List<Integer> baddies = new ArrayList<>();
            for(int j: occs.keySet()){
                if(occs.get(j) >= 2){
                    for(int k = 0; k < occs.get(j)/2; k++){
                        A.add(j);
                    }
                    for(int k = 0; k < occs.get(j)/2; k++){
                        B.add(j);
                    }
                }
                if(occs.get(j) % 2 == 1){
                    baddies.add(j);
                }
            }
            List<Integer> tmpA = new ArrayList<>(A);
            List<Integer> tmpB = new ArrayList<>(B);
            tmpA.addAll(baddies);
            Collections.sort(tmpA);
            int mex = 0;
            for(int j: tmpA){
                if(j == mex){
                    mex++;
                }
            }
            int mex1 = 0;
            for(int j : tmpB){
                if(j == mex1){
                    mex1++;
                }
            }
            int ans = mex + mex1;
            for(int j = 1; j < baddies.size(); j++){
                tmpA = new ArrayList<>(A);
                tmpB = new ArrayList<>(B);
                for(int k = 0; k < j; k++){
                    tmpA.add(baddies.get(k));
                }
                for(int k = j; k < baddies.size(); k++){
                    tmpB.add(baddies.get(k));
                }
                Collections.sort(tmpA);
                Collections.sort(tmpB);
                int Amex = 0, Bmex = 0;
                for(int k: tmpA){
                    if (Amex == k) {
                        Amex++;
                    }
                }
                for(int k: tmpB){
                    if(Bmex == k){
                        Bmex++;
                    }
                }
                ans = Math.max(ans, Amex + Bmex);
            }
            out.println(ans);
        }
        out.close();
    }
}