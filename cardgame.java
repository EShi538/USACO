import java.io.*;
import java.util.*;
public class cardgame {
    static boolean[] beaten;
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("cardgame.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        int[] elsie = new int[n];
        boolean[] visited = new boolean[n * 2 + 1];
        beaten = new boolean[n * 2 + 1];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            int x = Integer.parseInt(in.readLine());
            elsie[i] = x;
            visited[x] = true;
            set.add(x);
        }
        in.close();

        int[] bessie = new int[n];
        int index = 0;
        for(int i = 1; i <= n * 2; i++){
            if(!visited[i]){
                bessie[index] = i;
                index++;
            }
        }

        int[] elsieFirstHand = Arrays.copyOfRange(elsie, 0, n/2);
        int[] bessieFirstHand = Arrays.copyOfRange(bessie, n/2, n);
        int firstRound = highCard(bessieFirstHand, elsieFirstHand, n);
        List<Integer> remainingElsie = new ArrayList<Integer>();
        for(int i = 1; i <= n * 2; i++){
            if(!beaten[i] && set.contains(i)){
                remainingElsie.add(i);
            }
        }
        Collections.sort(remainingElsie);
        Collections.reverse(remainingElsie);
        int[] elsieSecondHand = Arrays.copyOfRange(elsie, n/2, n);
        Arrays.sort(elsieSecondHand);
        for(int i = 0; i < n/4; i++){
            int temp = elsieSecondHand[i];
            elsieSecondHand[i] = elsieSecondHand[n/2 - i - 1];
            elsieSecondHand[n/2 - i - 1] = temp;
        }
        int[] bessieSecondHand = Arrays.copyOfRange(bessie, 0, n/2);
        for(int i = 0; i < n/4; i++){
            int temp = bessieSecondHand[i];
            bessieSecondHand[i] = bessieSecondHand[n/2 - i - 1];
            bessieSecondHand[n/2 - i - 1] = temp;
        }
        int secondRound = lowCard(bessieSecondHand, elsieSecondHand, n);

        File out = new File("cardgame.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(firstRound + secondRound));
        writer.close();
    }
    static int highCard(int[] bessieFirstHand, int[] elsieFirstHand, int n){
        Arrays.sort(elsieFirstHand);
        int bessieIndex = 0;
        int elsieIndex = 0;
        int ans = 0;
        while(bessieIndex < n/2 && elsieIndex < n/2){
            if(bessieFirstHand[bessieIndex] > elsieFirstHand[elsieIndex]){
                beaten[elsieFirstHand[elsieIndex]] = true;
                bessieIndex++;
                elsieIndex++;
                ans++;
            }
            else{
                bessieIndex++;
            }
        }
        return ans;
    }
    static int lowCard(int[] bessieSecondHand, int[] elsieSecondHand, int n){
        int bessieIndex = 0;
        int elsieIndex = 0;
        int ans = 0;
        while(bessieIndex < n/2 && elsieIndex < n/2){
            if(bessieSecondHand[bessieIndex] < elsieSecondHand[elsieIndex]){
                bessieIndex++;
                elsieIndex++;
                ans++;
            }
            else{
                bessieIndex++;
            }
        }
        return ans;
    }
}