import java.util.*;
import java.io.*;
public class trapped {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("trapped.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        bale[] bales = new bale[n];
        int[] distances = new int[n - 1];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bales[i] = new bale(b, a);
        }
        in.close();

        Arrays.sort(bales, new sort());
        for(int i = 0; i < n - 1; i++){
            distances[i] = bales[i + 1].pos - bales[i].pos;
        }
        int ans = 0;
        //starting pos is after haybale i, before haybale i + 1
        for(int i = 0; i < n - 1; i++){
            int dist = distances[i];
            int left = i;
            int right = i + 1;
            int runningDist = dist;
            boolean escaped = false;
            while(true){
                if(runningDist > bales[left].size){
                    left--;
                    if(left == -1){
                        escaped = true;
                        break;
                    }
                    runningDist = runningDist + distances[left];
                }
                else if(runningDist > bales[right].size){
                    right++;
                    if(right == n){
                        escaped = true;
                        break;
                    }
                    runningDist = runningDist + distances[right - 1];
                }
                else{
                    break;
                }
            }
            if(!escaped){
                ans = ans + dist;
            }
        }
        File out = new File("trapped.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}

class bale{
    int pos;
    int size;
    public bale(int pos, int size){
        this.pos = pos;
        this.size = size;
    }
}

class sort implements Comparator<bale>{
    public int compare(bale a, bale b){
        return a.pos - b.pos;
    }
}