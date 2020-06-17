import java.util.Scanner;
/*
ID: origami5
LANG: JAVA
TASK: test
PROG: ride
*/
public class ride {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String comet = in.next();
        String group = in.next();

        int cometP = 1;
        int groupP = 1;
        for(int i = 0; i < comet.length(); i++){
            int mult = comet.charAt(i) - 64;
            cometP = (cometP * mult) % 47;
        }
        for(int i = 0; i < group.length(); i++){
            groupP = (groupP * (group.charAt(i) - 64)) % 47;
        }
        if(cometP % 47 == groupP % 47){
            System.out.print("GO");
        }
        else{
            System.out.print("STAY");
        }
        in.close();
    }
}
