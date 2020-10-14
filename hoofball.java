import java.io.*;
import java.util.*;
public class hoofball {
    static int n;
    static int[] a;
    static Map<Integer, Integer> adjList = new HashMap<>(), map = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("hoofball.in"));
        PrintWriter out = new PrintWriter(new File("hoofball.out"));
        n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        Arrays.sort(a);
        for(int i = 0; i < n; i++){
            if(i == 0){
                adjList.put(a[i], a[i + 1]);
            }
            else if(i == n - 1){
                adjList.put(a[i], a[i - 1]);
            }
            else{
                if(Math.abs(a[i] - a[i - 1]) < Math.abs(a[i] - a[i + 1])){
                    adjList.put(a[i], a[i - 1]);
                }
                else if(Math.abs(a[i] - a[i + 1]) < Math.abs(a[i] - a[i - 1])){
                    adjList.put(a[i], a[i + 1]);
                }
                else{
                    adjList.put(a[i], a[i - 1]);
                }
            }
        }
        for(int i = 0; i < n; i++){
            map.put(a[i], 0);
            for(int j = 0; j < n; j++){
                if(adjList.get(a[j]) == a[i]){
                    map.put(a[i], map.get(a[i]) + 1);
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(map.get(a[i]) == 0){
                ans++;
            }
        }
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < n; i++){
            if(!visited.contains(a[i]) && map.get(a[i]) == 1 && map.get(adjList.get(a[i])) == 1 && adjList.get(adjList.get(a[i])) == a[i]){
                visited.add(a[i]); visited.add(adjList.get(a[i]));
                ans++;
            }
        }
        out.println(ans);
        out.close();
    }
}