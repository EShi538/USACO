import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static String st = in.next();
    static String en = in.next();
    static Map<String, Boolean> dict = new HashMap<String, Boolean>();

    public static int BFS(){
        Queue<modW> q = new LinkedList<modW>();
        q.add(new modW(st, 0));
        while(!q.isEmpty()){
            modW curr = q.poll();
            for(int i = 0; i < curr.word.length(); i++){
                for(char j = 'a'; j <= 'z'; j++){
                    StringBuilder nW = new StringBuilder(curr.word);
                    nW.setCharAt(i, j);
                    String newWord = nW.toString();
                    if(dict.containsKey(newWord) && dict.get(newWord) == false){
                        q.add(new modW(newWord, curr.depth + 1));
                        dict.put(newWord, true);
                    }
                    if(newWord.equals(en)){
                        return curr.depth + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        while(in.hasNext()){
            String word = in.next();
            if(word.length() == st.length()){
                dict.put(word, false);
            }
        }
        System.out.println(BFS());
        in.close();
    }
}

class word{
    String word;
    boolean visited;
    public word(String word, boolean visited){
        this.word = word;
        this.visited = visited;
    }
}

class modW{
    String word;
    int depth;
    public modW(String word, int depth){
        this.word = word;
        this.depth = depth;
    }
}