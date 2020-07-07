import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class mountains {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("mountains.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        List<mountain> mounts = new ArrayList<mountain>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            mounts.add(new mountain(new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))));
        }
        in.close();

        Collections.sort(mounts, new leftSort());

        int maxEnd = -1;
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(!occluded(maxEnd, mounts.get(i))){
                ans++;
            }
            if(mounts.get(i).rb > maxEnd){
                maxEnd = mounts.get(i).rb;
            }
        }
        File out = new File("mountains.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
    public static boolean occluded(int maxEnd, mountain curr){
        return curr.rb <= maxEnd;
    }
}

class mountain{
    point peak;
    int lb;
    int rb;

    public mountain(point peak){
        this.peak = peak;
        findBase();
    }
    public void findBase(){
        this.lb = peak.x - peak.y;
        this.rb = peak.x + peak.y;
    }
}

class point{
    int x;
    int y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

    
class leftSort implements Comparator<mountain>{ 
    public int compare(mountain a, mountain b) { 
        if(a.lb != b.lb){
            return a.lb - b.lb; 
        }
        else{
            return b.rb - a.rb;
        }
    } 
} 
  