import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class socdist {
    public static void main(String[] args)throws Exception{
        FileReader reader = new FileReader("socdist.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<patch> patches = new ArrayList<patch>();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            patches.add(new patch(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        in.close();
        Collections.sort(patches, new sort());
        int best = -1;
        int lb = 0;
        int ub = 900000000;
        while(lb != ub){
            int guess = (lb + ub)/2;
            if(check(guess, patches, n, m)){
                lb = guess + 1;
                if(guess > best){
                    best = guess;
                }
            }
            else{
                ub = guess;
            }
        }
        File out = new File("socdist.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(best));
        writer.close();
    }
    public static boolean check(int guess, List<patch> patches, int n, int m){
        long tot = 0;
        long lastCowPos = patches.get(0).st - guess;
        for(int i = 0; i < m; i++){
            if(tot >= n){
                return true;
            }
            patch curr = patches.get(i);
            long st1 = 0;
            if(lastCowPos + guess > curr.en){
                continue;
            }
            if(lastCowPos + guess <= curr.st){
                st1 = curr.st;
            }
            else{
                st1 = lastCowPos + guess;
            }
            long numCows = (curr.en - st1)/guess + 1;
            tot = tot + numCows;
            lastCowPos = st1 + ((numCows - 1) * guess);
        }
        if(tot >= n){
            return true;
        }
        return false;
    }
}

class patch{
    long st;
    long en;
    public patch(long st, long en){
        this.st = st;
        this.en = en;
    }
}

class sort implements Comparator<patch>{
    public int compare(patch a, patch b){
        return (int)(a.st - b.st);
    }
}