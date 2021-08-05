import java.io.*;
import java.util.*;
public class shuffle {
    static int n;
    static int[] a;
    static boolean[] visited;
    static int ans = 0;
    static HashSet<Integer> alreadyCycleCounted = new HashSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
        n = Integer.parseInt(in.readLine());
        a = new int[n + 1];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                findCycle(i);
            }
        }
        FileWriter out = new FileWriter("shuffle.out");
        out.write(Integer.toString(ans));
        out.close();
    }
    static void findCycle(int node){
        visited[node] = true;
        if(!visited[a[node]]){
            findCycle(a[node]);
        }
        else if(visited[a[node]] && !alreadyCycleCounted.contains(a[node])){
            ans += cycleLength(a[node]);
        }
    }
    static int cycleLength(int start){
        int currNode = start;
        int length = 0;
        while(length == 0 || currNode != start){
            alreadyCycleCounted.add(currNode);
            length++;
            currNode = a[currNode];
        }
        return length;
    }
}
