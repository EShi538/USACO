/*
ID: origami5
LANG: JAVA
TASK: friday
*/
import java.io.*;
public class friday {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("friday.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        in.close();

        int[] days = new int[8];
        int currDay = 1;
        boolean leap = false;
        for(int i = 1900; i < 1900 + n; i++){
            if((i % 4 == 0 && i % 100 != 0) || (i % 400 == 0)){
                leap = true;
            }
            for(int j = 1; j <= 12; j++){
                if(j == 9 || j == 4 || j == 6 || j == 11){
                    for(int k = 1; k <= 30; k++){
                        if(k == 13){
                            days[currDay]++;
                        }
                        currDay++;
                        if(currDay > 7){
                            currDay = 1;
                        }
                    }
                }
                else{
                    if(j == 2 && leap == false){
                        for(int k = 1; k <= 28; k++){
                            if(k == 13){
                                days[currDay]++;
                            }
                            currDay++;
                            if(currDay > 7){
                                currDay = 1;
                            }
                        }
                    }
                    else if(j == 2 && leap == true){
                        leap = false;
                        for(int k = 1; k <= 29; k++){
                            if(k == 13){
                                days[currDay]++;
                            }
                            currDay++;
                            if(currDay > 7){
                                currDay = 1;
                            }
                        }
                    }
                    else{
                        for(int k = 1; k <= 31; k++){
                            if(k == 13){
                                days[currDay]++;
                            }
                            currDay++;
                            if(currDay > 7){
                                currDay = 1;
                            }
                        }
                    }
                }
            }
        }
        File out = new File("friday.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(days[6]) + " " + Integer.toString(days[7]) + " " + Integer.toString(days[1]) + " " + Integer.toString(days[2]) + " " + Integer.toString(days[3]) + " " + Integer.toString(days[4]) + " " + Integer.toString(days[5]) + "\n");
        writer.close();
    }
}
