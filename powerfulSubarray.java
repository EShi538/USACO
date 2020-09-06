import java.io.*;
import java.util.*;
public class powerfulSubarray {
    static int n;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        in.close();
        out.println(solve());
        out.close();
    }
    static int solve(){
        int ans = n + 1, l = 2, r = n;
        while(l <= r){
            int m = (l + r)/2;
            if(valid(m)){
                ans = Math.min(m, ans);
                r = m - 1;
            }
            else{
                l = m + 1;
            }
        }
        return ans;
    }
    static boolean valid(int m){
        int l = 0, r = m - 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < m; i++){
            if(!map.containsKey(a[i])){
                map.put(a[i], 1);
            }
            else{
                map.put(a[i], map.get(a[i]) + 1);
            }
        }
        TreeMap<Integer, Set<Integer>> rev = new TreeMap<>();
        for(int i: map.keySet()){
            Set<Integer> s;
            s = (rev.containsKey(map.get(i))) ? rev.get(map.get(i)) : new HashSet<>();
            s.add(i);
            rev.put(map.get(i), s);
        }
        if(rev.get(rev.lastKey()).size() == 1){
            return true;
        }
        for(int i = 1; i < n - m; i++){
            l = i;
            r = i + m - 1;
            int remove = a[l - 1];
            int add = a[r];
            rev.get(map.get(remove)).remove(remove);
            if(rev.get(map.get(remove)).size() == 0){
                rev.remove(map.get(remove));
            }
            map.put(remove, map.get(remove) - 1);
            if(rev.containsKey(map.get(remove))){
                Set<Integer> s = rev.get(map.get(remove));
                s.add(remove);
                rev.put(map.get(remove), s);
            }
            else{
                Set<Integer> s = new HashSet<>();
                s.add(remove);
                rev.put(map.get(remove), s);
            }
            if(map.containsKey(add)){
                map.put(add, map.get(add) + 1);
            }
            else{
                map.put(add, 1);
            }
            if(rev.containsKey(map.get(add))){
                Set<Integer> s = rev.get(map.get(add));
                s.add(add);
                rev.put(map.get(add), s);
            }
            else{
                Set<Integer> s = new HashSet<>();
                s.add(add);
                rev.put(map.get(add), s);
            }
            if(rev.get(rev.lastKey()).size() == 1){
                return true;
            }
        }
        return false;
    }

}
