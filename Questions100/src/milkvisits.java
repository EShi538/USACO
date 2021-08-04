import java.io.*;
import java.util.*;
public class milkvisits {
    static int n, m;
    static String cows;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static List<visit> visits = new ArrayList<>();
    static char[] cowTypes;
    static boolean[] visited;
    static boolean finished;
    static boolean found;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("milkvisits.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cows = in.readLine();
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b); adjList.get(b).add(a);
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            char preference = st.nextToken().charAt(0);
            visits.add(new visit(a, b, preference));
        }
        in.close();
        cowTypes = new char[n + 1];
        for(int i = 1; i <= n; i++){
            cowTypes[i] = cows.charAt(i - 1);
        }
        FileWriter out = new FileWriter("milkvisits.out");
        for(visit i: visits){
            visited = new boolean[n + 1];
            finished = false;
            found = false;
            dfs(i.a, new HashSet<>(), i.b, i.preference);
            if(found){
                out.write("1");
            }
            else{
                out.write("0");
            }
        }
        out.close();
    }
    static void dfs(int node, HashSet<Character> typesFound, int target, char favorite){
        visited[node] = true;
        typesFound.add(cowTypes[node]);
        if(node == target){
            if(typesFound.contains(favorite)){
                found = true;
            }
            finished = true;
            return;
        }
        for(int i: adjList.get(node)){
            if(!visited[i]){
                HashSet<Character> typesFoundPrev = (HashSet) typesFound.clone();
                dfs(i, typesFound, target, favorite);
                typesFound = (HashSet) typesFoundPrev.clone();
                if(finished){
                    return;
                }
            }
        }
        return;
    }
}
class visit{
    int a, b;
    char preference;
    public visit(int a, int b, char preference){
        this.a = a; this.b = b;
        this.preference = preference;
    }
}
