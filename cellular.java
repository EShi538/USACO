import java.io.*;
import java.util.*;
public class cellular {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<structure> structures = new ArrayList<>();
        List<Integer> towers = new ArrayList<Integer>();
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            structures.add(new structure(Integer.parseInt(st.nextToken()), false));
        }
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < m; i++){
            int x = Integer.parseInt(st.nextToken());
            structures.add(new structure(x, true));
        }
        in.close();

        structures.sort(new sort());
        int lTower = 0; int rTower = 0; int towerIndex = 0;

        int towersProcessed = 0;
        for(int i = 0; i < structures.size(); i++){
            if(structures.get(i).tower){
                towers.add(i);
            }
        }
        if(towers.size() == 1){
            if(structures.get(0).tower){
                rTower = -2;
                towerIndex = 1;
            }
            else{
                lTower = -1;
                rTower = towers.get(0);
            }
        }
        else{
            if(structures.get(0).tower){
                for(int i = 1; i < structures.size(); i++){
                    if(structures.get(i).tower){
                        rTower = i;
                        towerIndex = 2;
                        break;
                    }
                }
            }
            else{
                lTower = -1;
                for(int i = 1; i < structures.size(); i++){
                    if(structures.get(i).tower){
                        towersProcessed++;
                        towerIndex = 1;
                        rTower = i;
                        break;
                    }
                }
            }
        }
        int ans = -1;
        for (structure structure : structures) {
            if (structure.tower) {
                if (towersProcessed > 0) {
                    towersProcessed++;
                    lTower = rTower;
                    if (towerIndex < m) {
                        rTower = towers.get(towerIndex);
                        towerIndex++;
                    }
                    else {
                        rTower = -2;
                    }
                    continue;
                }
                else {
                    towersProcessed++;
                    continue;
                }
            }
            int minDist = 0;
            if (rTower != -2 && lTower != -1) {
                minDist = Math.min(structure.pos - structures.get(lTower).pos, structures.get(rTower).pos - structure.pos);
            }
            else if (lTower == -1) {
                minDist = structures.get(rTower).pos - structure.pos;
            }
            else {
                minDist = structure.pos - structures.get(lTower).pos;
            }
            if (minDist > ans) {
                ans = minDist;
            }
        }
        System.out.println(ans);
    }
}

class structure{
    int pos;
    boolean tower;
    public structure(int pos, boolean tower){
        this.pos = pos;
        this.tower = tower;
    }
}

class sort implements Comparator<structure>{
    public int compare(structure a, structure b){
        return a.pos - b.pos;
    }
}