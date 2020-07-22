import java.io.*;
import java.util.*;
public class diamond {
    public static void main(String[] args) throws Exception {
        //input
        FileReader reader = new FileReader("diamond.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Integer> diamonds = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            diamonds.add(Integer.parseInt(in.readLine()));
        }
        in.close();
        Collections.sort(diamonds);

        TreeSet<Integer> sizes = new TreeSet<Integer>(diamonds);
        List<List<Integer>> mapUpper = new ArrayList<List<Integer>>();
        List<List<Integer>> mapLower = new ArrayList<List<Integer>>();
        int index = 0;
        for (int i : diamonds) {
            int lowerTarget = sizes.tailSet(i - k).first();
            int lower = binSearch(lowerTarget, -1, diamonds);
            int upperTarget = sizes.headSet(i + k, true).last();
            int upper = binSearch(upperTarget, 1, diamonds);
            mapUpper.add(diamonds.subList(index, upper + 1));
            mapLower.add(diamonds.subList(lower, index + 1));
            index++;
        }
        int[] maxBefore = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                maxBefore[0] = mapLower.get(0).size();
                continue;
            }
            maxBefore[i] = Math.max(mapLower.get(i).size(), maxBefore[i - 1]);
        }
        int max = -1;
        for(int i = 0; i < n; i++){
            int before = -1;
            if(i != 0){
                before = maxBefore[i - 1];
            }
            else{
                before = 0;
            }
            if(before + mapUpper.get(i).size() > max){
                max = before + mapUpper.get(i).size();
            }
        }
        File out = new File("diamond.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(max));
        writer.close();
    }
    static int binSearch(int target, int direction, List<Integer> diamonds){
        int lb = 0;
        int ub = diamonds.size();
        while(lb != ub){
            int m = (lb + ub)/2;
            if(diamonds.get(m) == target){
                int index = m;
                while(index >= 0 && index < diamonds.size() && diamonds.get(index).equals(diamonds.get(m))){
                    index = index + direction;
                }
                index = index - direction;
                lb = index;
                ub = index;
            }
            else if(diamonds.get(m) < target){
                lb = m + 1;
            }
            else{
                ub = m;
            }
        }
        return lb;
    }
}
