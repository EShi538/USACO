import java.io.*;
import java.util.*;

public class rescue {
    static int n, ans;
    static String a, b;
    static List<ArrayList<Character>> adjList = new ArrayList<>();
    static List<output> outputs = new ArrayList<>();
    static boolean[] visited;
    //a -> 1, b -> 2; c -> 3...
    static boolean good = false;
    static void check(char node, char target){
        if(node == target){
            good = true;
            return;
        }
        visited[node - 96] = true;
        List<Character> adj = adjList.get(node - 96);
        for(char i: adj){
            if(!visited[i - 96]){
                check(i, target);
                if(good){
                    return;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        a = in.readLine();
        b = in.readLine();
        for(int i = 0; i <= 26; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n; i++){
            visited = new boolean[27];
            check(a.charAt(i), b.charAt(i));
            if(good){
                good = false;
            }
            else{
                ans++;
                adjList.get(a.charAt(i) - 96).add(b.charAt(i));
                adjList.get(b.charAt(i) - 96).add(a.charAt(i));
                outputs.add(new output(a.charAt(i), b.charAt(i)));
            }
        }
        System.out.println(ans);
        for(output i: outputs){
            System.out.println(i.a + " " + i.b);
        }
        in.close();
    }
}

class output{
    char a, b;
    public output(char a, char b){
        this.a = a; this.b = b;
    }
}