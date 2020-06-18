import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int C = in.nextInt();
        List<Integer> ons = new ArrayList<Integer>();
        while(true){
            int x = in.nextInt();
            if(x == -1){
                break;
            }
            ons.add(x);
        }
        List<Integer> offs = new ArrayList<Integer>();
        while(true){
            int x = in.nextInt();
            if(x == -1){
                break;
            }
            offs.add(x);
        }
        in.close();

        List<String> ans = new ArrayList<String>();
        //run through the 16 states
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                for(int e = 0; e < 2; e++){
                    for(int k = 0; k < 2; k++){
                        boolean[] construct = new boolean[7];
                        String lights = "";
                        int numOn = 0;
                        //make the instruction string and calculate the number of 1's
                        if(i == 1){
                            numOn++;
                            lights = lights + "1";
                        }
                        else{
                            lights = lights + "0";
                        }
                        if(j == 1){
                            numOn++;
                            lights = lights + "1";
                        }
                        else{
                            lights = lights + "0";
                        }
                        if(e == 1){
                            numOn++;
                            lights = lights + "1";
                        }
                        else{
                            lights = lights + "0";
                        }
                        if(k == 1){
                            numOn++;
                            lights = lights + "1";
                        }
                        else{
                            lights = lights + "0";
                        }

                        //check C condition
                        if(!(numOn <= C && numOn % 2 == C % 2)){
                            continue;
                        }

                        //construct the array of size 6 given constraints
                        for(int x = 0; x <= 6; x++){
                            construct[x] = true;
                        }
                        if(lights.charAt(0) == '1'){
                            for(int x = 1; x <= 6; x++){
                                if(construct[x] == true){
                                    construct[x] = false;
                                }
                                else{
                                    construct[x] = true;
                                }
                            }
                        }
                        if(lights.charAt(1) == '1'){
                            for(int x = 1; x <= 6; x = x + 2){
                                if(construct[x] == true){
                                    construct[x] = false;
                                }
                                else{
                                    construct[x] = true;
                                }
                            }
                        }
                        if(lights.charAt(2) == '1'){
                            for(int x = 2; x <= 6; x = x + 2){
                                if(construct[x] == true){
                                    construct[x] = false;
                                }
                                else{
                                    construct[x] = true;
                                }
                            }
                        }
                        if(lights.charAt(3) == '1'){
                            for(int x = 1; x <= 6; x = x + 3){
                                if(construct[x] == true){
                                    construct[x] = false;
                                }
                                else{
                                    construct[x] = true;
                                }
                            }
                        }

                        //check if it satisfies the other two constraints (true = on, false = off)
                        boolean bad = false;
                        for(int x = 0; x < ons.size(); x++){
                            int pos = ons.get(x) % 6;
                            if(pos == 0){
                                pos = 6;
                            }
                            if(construct[pos] == false){
                                bad = true;
                                break;
                            }
                        }
                        if(bad == true){
                            continue;
                        }
                        for(int x = 0; x < offs.size(); x++){
                            int pos = offs.get(x) % 6;
                            if(pos == 0){
                                pos = 6;
                            }
                            if(construct[pos] == true){
                                bad = true;
                                break;
                            }
                        }
                        if(bad == true){
                            continue;
                        }
                        //output if it works
                        List<Integer> output = new ArrayList<Integer>();
                        for(int x = 0; x < N/6; x++){
                            for(int y = 1; y <= 6; y++){
                                if(construct[y] == true){
                                    output.add(1);
                                }
                                else{
                                    output.add(0);
                                }
                            }
                        }
                        for(int x = 1; x <= N % 6; x++){
                            if(construct[x] == false){
                                output.add(0);
                            }
                            else{
                                output.add(1);
                            }
                        }
                        
                        String num = "";
                        for(int x = 0; x < output.size(); x++){
                            num = num + output.get(x);
                        }
                        ans.add(num);
                    }
                }
            }
        }
        if(ans.size() == 0){
            System.out.println("IMPOSSIBLE");
            return;
        }
        Collections.sort(ans);
        for(String i: ans){
            System.out.println(i);
        }
    }
}
  