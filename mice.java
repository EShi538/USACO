import java.io.*;
import java.util.*;

public class mice {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int USB = Integer.parseInt(st.nextToken());
        int PS = Integer.parseInt(st.nextToken());
        int both = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(in.readLine());
        List<mouse> mice = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int cost = Integer.parseInt(st.nextToken());
            String type = st.nextToken();
            mice.add(new mouse(cost, type));
        }
        in.close();

        mice.sort(new sort());
        long totCost = 0;
        long num = 0;
        for(mouse i: mice){
            if(i.type.equals("USB")){
                if(USB == 0 && both != 0){
                    both--;
                    num++;
                    totCost += i.cost;
                }
                else if(USB != 0){
                    USB--;
                    num++;
                    totCost += i.cost;
                }

            }
            else{
                if(PS == 0 && both != 0){
                    both--;
                    num++;
                    totCost += i.cost;
                }
                else if(PS != 0){
                    PS--;
                    num++;
                    totCost += i.cost;
                }
            }
        }
        System.out.println(num + " " + totCost);
    }
}

class mouse{
    int cost;
    String type;
    public mouse(int cost, String type){
        this.cost = cost;
        this.type = type;
    }
}

class sort implements Comparator<mouse>{
    public int compare(mouse a, mouse b){
        return a.cost - b.cost;
    }
}