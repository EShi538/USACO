import java.io.*;
import java.util.*;
public class happiness {
    static int[] peoplePassThrough, people, happinessIndex;
    static int n, m;
    static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static boolean done = false, good = true;
    static int passThrough(int node, int prev){
        List<Integer> adj = adjList.get(node);
        peoplePassThrough[node] += people[node];
        for(int i: adj){
            if(i != prev){
                peoplePassThrough[node] += passThrough(i, node);
            }
        }
        return peoplePassThrough[node];
    }
    static void check(int node, int prev){
        List<Integer> adj = adjList.get(node);
        int sum = 0;
        int happyCnt = (happinessIndex[node] + peoplePassThrough[node])/2;
        for(int i: adj){
            if(i != prev){
                int A = (happinessIndex[i] + peoplePassThrough[i]);
                if(((A % 2) + 2) % 2 == 1 || A/2 > peoplePassThrough[i] || A/2 < 0){
                    good = false;
                    done = true;
                    return;
                }
                sum += A/2;
            }
        }
        if(sum > happyCnt){
            good = false;
            done = true;
            return;
        }
        for(int i: adj){
            if(i != prev){
                check(i, node);
                if(done){
                    return;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            people = new int[n + 1];
            st = new StringTokenizer(in.readLine());
            for(int j = 1; j <=n; j++){
                people[j] = Integer.parseInt(st.nextToken());
            }
            happinessIndex = new int[n + 1];
            st = new StringTokenizer(in.readLine());
            for(int j = 1; j <=n; j++){
                happinessIndex[j] = Integer.parseInt(st.nextToken());
            }
            adjList = new ArrayList<ArrayList<Integer>>();
            for(int j = 0; j <= n; j++){
                adjList.add(new ArrayList<Integer>());
            }
            for(int j = 0; j < n - 1; j++){
                st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }
            peoplePassThrough = new int[n + 1];
            passThrough(1, -1);
            if((((peoplePassThrough[1] + happinessIndex[1]) % 2) + 2) % 2 == 1){
                System.out.println("NO");
                continue;
            }
            int index = (peoplePassThrough[1] + happinessIndex[1])/2;
            if(index < 0 || index > peoplePassThrough[1]){
                System.out.println("NO");
                continue;
            }
            done = false;
            good = true;
            check(1, -1);
            if(!good){
                System.out.println("NO");
            }
            else{
                System.out.println("YES");
            }
        }
    }
}
