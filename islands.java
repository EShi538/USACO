import java.io.*;
import java.util.*;

public class islands {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("islands.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        section[] field = new section[n];
        for(int i = 0; i < n; i++){
            field[i] = new section(Integer.parseInt(in.readLine()), i);
        }
        in.close();

        Arrays.sort(field, new sort());
        StringBuilder status = new StringBuilder("");
        for(int i = 0; i < n; i++){
            status = new StringBuilder(status.toString() + "L");
        }

        int ans = -1;
        int islandCnt = 1;
        for(int i = 0; i < n; i++){
            int index = i;
            int currHeight = field[i].height;
            List<section> change = new ArrayList<section>();
            while(index < n && field[index].height == currHeight){
                change.add(field[index]);
                index++;
            }
            i = index - 1;
            for(section j: change){
                status.setCharAt(j.ID, 'W');
                if(j.ID > 0 && j.ID < n - 1){
                    if(status.charAt(j.ID - 1) == 'W' && status.charAt(j.ID + 1) == 'W'){
                        islandCnt--;
                    }
                    else if(status.charAt(j.ID - 1) == 'L' && status.charAt(j.ID + 1) == 'L'){
                        islandCnt++;
                    }
                }
                else if(j.ID == 0){
                    if(status.charAt(j.ID + 1) == 'W'){
                        islandCnt--;
                    }
                }
                else if(j.ID == n - 1){
                    if(status.charAt(j.ID - 1) == 'W'){
                        islandCnt--;
                    }
                }
            }
            if(islandCnt > ans){
                ans = islandCnt;
            }
        }
        File out = new File("islands.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}

class section{
    int height;
    int ID;
    public section(int height, int ID){
        this.height = height;
        this.ID = ID;
    }
}

class sort implements Comparator<section>{
    public int compare(section a, section b){
        return a.height - b.height;
    }
}