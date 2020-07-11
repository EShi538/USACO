import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class lifeguards {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("lifeguards.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        List<time> times = new ArrayList<time>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            times.add(new time(a, (i + 1)));
            times.add(new time(b, -(i + 1)));
        }
        in.close();

        int tot = 0;
        Collections.sort(times, new sortTime());
        TreeSet<Integer> onDuty = new TreeSet<Integer>();
        int[] alone = new int[n];
        for(int i = 0; i < times.size() - 1; i++){
            if(times.get(i).type > 0){
                onDuty.add(times.get(i).type);
            }
            else if(times.get(i).type < 0){
                onDuty.remove(-(times.get(i).type));
            }
            
            int length = times.get(i + 1).point - times.get(i).point;
            if(onDuty.size() == 1){
                alone[onDuty.first() - 1] = alone[onDuty.first() - 1] + length;
            }
            if(!onDuty.isEmpty()){
                tot = tot + length;
            }
        }
        int min = Arrays.stream(alone).min().getAsInt();
        File out = new File("lifeguards.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(tot - min));
        writer.close();
    }
}

class time{
    int point;
    int type;
    public time(int point, int type){
        this.point = point;
        this.type = type;
    }
}

class sortTime implements Comparator<time>{
    public int compare(time a, time b){
        return a.point - b.point;
    }
}