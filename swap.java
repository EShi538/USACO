import java.util.*;

import java.io.*;
public class swap { 
    static boolean[] visited;
    static List<ArrayList<Integer>> chains = new ArrayList<ArrayList<Integer>>();
    public static void findCycle(int st, int[] map, int index){
        Stack<Integer> todo = new Stack<Integer>();
        todo.push(st);
        while(!todo.isEmpty()){
            int node = todo.pop();
            if(visited[node] == true){
                return;
            }
            visited[node] = true;
            chains.get(index).add(node);
            todo.add(map[node]);
        }
        return;
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("swap.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<reverse> swaps = new ArrayList<reverse>();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            swaps.add(new reverse(a, b));
        }
        in.close();

        int[] map = new int[n + 1];
        int[] line = new int[n + 1];
        for(int i = 1; i <= n; i++){
            line[i] = i;
        }
        File out = new File("swap.out");
        FileWriter writer = new FileWriter(out);
        for(int i = 0; i < m; i++){
            reverse curr = swaps.get(i);
            int start = curr.a; int end = curr.b;
            while(start < end){
                int temp = line[start];
                line[start] = line[end];
                line[end] = temp;
                end--;
                start++;
            }
        }
        for(int i = 1; i <= n; i++){
            map[line[i]] = i;
        } 
        visited = new boolean[n + 1];
        int[] whichChain = new int[n + 1];
        int[] chainPos = new int[n + 1];
        int max = -1;
        int index = 0;
        List<Integer> sizes = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++){
            if(visited[i] == false){
                chains.add(new ArrayList<Integer>());
                findCycle(i, map, index);
                if(chains.get(index).size() > max){
                    max = chains.get(index).size();
                }
                if(chains.get(index).size() != 1){
                    sizes.add(chains.get(index).size());
                }
                index++;
            }
        }
        for(int i = 0; i < chains.size(); i++){
            for(int j = 0; j < chains.get(i).size(); j++){
                whichChain[chains.get(i).get(j)] = i;
                chainPos[chains.get(i).get(j)] = j;
            }
        }
        long mod = findGCD(sizes, k);
        int amount = (int)(k % mod);
        int[] ans = new int[n + 1];
        for(int i = 1; i <= n; i++){
            List<Integer> chain = chains.get(whichChain[i]);
            int pos = chainPos[i];
            int ind = pos + amount;
            if(ind >= chain.size()){
                ind = ind % chain.size();
            }
            ans[chain.get(ind)] = i;
        }
        for(int i = 1; i <= n; i++){
            writer.write(Integer.toString(ans[i]) + "\n");
        }
        writer.close();
    }
    public static long GCD(long a, long b){
        if(b == 0){
            return a;
        }
        return GCD(b, a % b);
    }
    public static long findGCD(List<Integer> sizes, int k){
        long res = sizes.get(0);
        for(int i: sizes){
            if(res > k){
                return res;
            }
            res = (res * i)/(GCD(i, res));
        }
        return res;
    }
}

class reverse{
    int a;
    int b;
    public reverse(int a, int b){
        this.a = a;
        this.b = b;
    }
}