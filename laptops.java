import java.io.*;
import java.util.*;
public class rebrand {
    static int n;
    static List<laptop> laptops = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int speed = Integer.parseInt(st.nextToken());
            int ram = Integer.parseInt(st.nextToken());
            int hdd = Integer.parseInt(st.nextToken());
            int cost =Integer.parseInt(st.nextToken());
            laptops.add(new laptop(speed, ram, hdd, cost, i + 1));
        }
        in.close();

        List<Integer> remove = new ArrayList<>();
        for(int i = 0; i < laptops.size(); i++){
            laptop curr = laptops.get(i);
            boolean outdated = false;
            for(int j = 0; j < laptops.size(); j++){
                if(j == i){
                    continue;
                }
                laptop other = laptops.get(j);
                if (curr.ram < other.ram && curr.hdd < other.hdd && curr.speed < other.speed) {
                    outdated = true;
                    break;
                }
            }
            if(outdated){
                outdated = false;
                remove.add(i + 1);
            }
        }
        laptops.sort(new sort());
        for(laptop i: laptops){
            if(!remove.contains(i.ID)){
                System.out.print(i.ID);
                return;
            }
        }
    }
}

class laptop{
    int speed, ram, hdd, cost, ID;
    public laptop(int speed, int ram, int hdd, int cost, int ID){
        this.speed = speed;
        this.ram = ram;
        this.hdd = hdd;
        this.cost = cost;
        this.ID = ID;
    }
}

class sort implements Comparator<laptop>{
    public int compare(laptop a, laptop b){
        return a.cost - b.cost;
    }
}
