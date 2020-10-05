import java.io.*;
import java.util.*;
public class planting {
    static int n;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new File("planting.out"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < n - 1; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        in.close();
        int ans = -1;
        for(List<Integer> i: adjList){
            ans = Math.max(i.size(), ans);
        }
        out.println(ans + 1);
        out.close();
    }
}
