import java.io.*;
import java.util.*;
public class planting1 {
    static int n;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("planting.in"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n - 1; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b); adjList.get(b).add(a);
        }
        in.close();
        int ans = 0;
        for(int i = 1; i <= n; i++){
            ans = Math.max(ans, adjList.get(i).size() + 1);
        }
        FileWriter out = new FileWriter("planting.out");
        out.write(Integer.toString(ans));
        out.close();
    }
}
