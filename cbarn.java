import java.io.*;
import java.util.*;
public class cbarn {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("cbarn.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        int[] line = new int[n];
        for(int i = 0; i < n; i++){
            line[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        int[] cows = new int[n];
        int ans = 0;
        Set<Integer> toMove = new HashSet<Integer>();
        int[] root = new int[n];
        boolean justStarted = true;
        for(int i = 0; i < n; i++){
            if(line[i] != 0){
                int index = i;
                int shift = 0;
                int firstIndex = 0;
                //move cows from current line                    
                for(int j = 0; j < line[i]; j++){
                    while(cows[index] != 0){
                        index++;
                        if(index >= n){
                            index = 0;
                        }
                    }
                    if(j == 0){
                        firstIndex = index;
                    }
                    cows[index]++;
                    root[index] = i;
                }
                line[i] = 0;
                int ind = firstIndex - 1;
                if(ind < 0){
                    ind = n - 1;
                }
                while(cows[ind] == 0){
                    ind--;
                    shift++;
                    if(ind < 0){
                        ind = n - 1;
                    }
                }

                //shift the other cows over
                int[] temp = root.clone();
                if(justStarted == false){
                    for(int j: toMove){
                        int nInd = j + shift;
                        if(nInd > n){
                            nInd = nInd % cows.length;
                        }
                        cows[nInd]++;
                        cows[j]--;
                        root[nInd] = root[nInd] + temp[j];
                        root[j] = root[j] - temp[j];
                    }
                }
                else{
                    justStarted = false;
                }
                for(int j = 0; j < n; j++){
                    if(cows[j] == 1){
                        toMove.add(j);
                    }
                    else if(cows[j] == 0 && toMove.contains(j)){
                        toMove.remove((Object)j);
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            int dist;
            if(root[i] > i){
                dist = ((n - 1) + i) - root[i] + 1;
            }
            else{
                dist = root[i] - i;
            }
            ans = ans + (int)Math.pow(dist, 2);
        }

        File out = new File("cbarn.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}