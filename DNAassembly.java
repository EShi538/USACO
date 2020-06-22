import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static int n = in.nextInt();
    static List<word> words = new ArrayList<word>();
    static List<String> perm = new ArrayList<String>();
    static int min = Integer.MAX_VALUE;

    public static String merge(String a, String b){
        if(a.length() != 0){
            int max = -1;
            int j = Math.min(a.length() - 1, b.length() - 1);
            String best = "";
            for(int i = j; i >= 0; i--){
                String subB = b.substring(0, i + 1);
                String subA = a.substring(a.length() - 1 - i, a.length());
                if(subB.equals(subA)){
                    if(subB.length() > max){
                        max = subB.length();
                        best = subB;
                    }
                }
            }
            StringBuilder input = new StringBuilder(a);
            input.replace(a.length() -  best.length(), a.length(), "");
            String out = input.toString() + b;
            return out;
        }
        else{
            return b;
        }
    }

    public static void solve(int x){
        if(x == n){
            String out = "";
            for(int i = 0; i < n; i++){
                out = merge(out, perm.get(i));
            }
            if(out.length() < min){
                min = out.length();
            }
            perm.remove(perm.size() - 1);
            return;
        }
        for(int i = 0; i < n; i++){
            if(words.get(i).visited == false){
                perm.add(words.get(i).word);
                words.get(i).visited = true;
                solve(x + 1);
                words.get(i).visited = false;
            }
        }
        if(perm.size() == 0){
            return;
        }
        perm.remove(perm.size() - 1);
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < n; i++){
            words.add(new word(in.next()));
        }
        in.close();
        solve(0);
        System.out.println(min);
    }
}

class word{
    String word;
    boolean visited;
    public word(String word){
        this.word = word;
        this.visited = false;
    }
}