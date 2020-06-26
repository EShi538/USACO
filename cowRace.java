import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<sect> Bsections = new ArrayList<sect>();
        List<sect> Esections = new ArrayList<sect>();
        int totTime = 0;
        for(int i = 0; i < n; i++){
            int spd = in.nextInt();
            int time = in.nextInt();
            totTime = totTime + time;
            Bsections.add(new sect(spd, time));
        }
        for(int i = 0; i < m; i++){
            Esections.add(new sect(in.nextInt(), in.nextInt()));
        }
        in.close();

        List<Integer> Btot = new ArrayList<Integer>();
        List<Integer> Etot = new ArrayList<Integer>();
        int j = 0;
        for(int i = 0; i < n; i++){
            j = 0;
            while(j < Bsections.get(i).time){
                Btot.add(Bsections.get(i).spd);
                j++;
            }
        }
        int k = 0;
        for(int i = 0; i < m; i++){
            k = 0;
            while(k < Esections.get(i).time){
                Etot.add(Esections.get(i).spd);
                k++;
            }
        }

        int cnt = 0;
        int Bpos = 0;
        int Epos = 0;
        int leader = -1;
        int prevleader = -1; //0 = bessie; 1 = elsie; -1 = NO ONE
        for(int i = 0; i <= totTime; i++){
            prevleader = leader;
            if(Bpos > Epos){
                leader = 0;
            }
            else if(Epos > Bpos){
                leader = 1;
            }
            if(leader != prevleader && prevleader != -1){
                cnt++;
            }
            if(i != totTime){
                Bpos = Bpos + Btot.get(i);
                Epos = Epos + Etot.get(i);
            }
        }
        System.out.println(cnt);
    }
}

class sect{
    int spd;
    int time;
    public sect(int spd, int time){
        this.spd = spd;
        this.time = time;
    }
}
