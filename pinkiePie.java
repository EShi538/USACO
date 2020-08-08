import java.io.*;
import java.util.*;
public class pinkiePie {
    static TreeMap<Integer, TreeSet<Integer>> templ = new TreeMap<>(new sort1());
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++){
            templ.clear();
            int n = Integer.parseInt(in.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            List<occ> occs = new ArrayList<>();
            for(int j = 0; j < n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            for(int j = 0; j < n; j++){
                int index = j, cnt = 0;
                while(index < n && arr[index] == arr[j]){
                    index++;
                    cnt++;
                }
                j = index - 1;
                occs.add(new occ(arr[j], cnt));
            }
            for(occ j: occs){
                TreeSet<Integer> put;
                if(!templ.containsKey(j.occurrences)){
                    put = new TreeSet<>();
                }
                else{
                    put = templ.get(j.occurrences);
                }
                put.add(j.num);
                templ.put(j.occurrences, put);
            }
            occs.sort(new sort());
            System.out.println(solve(occs, n));
        }
    }
    static int solve(List<occ> occs, int n){
        int ans = 0;
        int lb = 0, ub = n;
        while(lb != ub){
            int m = (lb + ub)/2;
            if(good(occs, n, m)){
                lb = m + 1;
                ans = Math.max(ans, m);
            }
            else{
                ub = m;
            }
        }
        return ans;
    }
    static boolean good(List<occ> occs, int n, int gap){
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>(new sort1());
        for(int i: templ.keySet()){
            TreeSet<Integer> put = new TreeSet<>(templ.get(i));
            map.put(i, put);
        }
        Queue<occ> unusable = new LinkedList<>();
        int[] fill = new int[n];
        for(int i = 0; i < gap; i++){
            if(map.isEmpty()){
                return false;
            }
            fill[i] = map.get(map.firstKey()).first();
            unusable.add(new occ(map.get(map.firstKey()).first(), map.firstKey()));
            map.get(map.firstKey()).remove(map.get(map.firstKey()).first());
            if(map.get(map.firstKey()).size() == 0){
                map.remove(map.firstKey());
            }
        }
        for(int i = gap; i < n; i++){
            if(map.isEmpty()){
                return false;
            }
            fill[i] = map.get(map.firstKey()).first();
            occ addBack = unusable.poll();
            unusable.add(new occ(map.get(map.firstKey()).first(), map.firstKey()));
            map.get(map.firstKey()).remove(map.get(map.firstKey()).first());
            if(map.get(map.firstKey()).size() == 0){
                map.remove(map.firstKey());
            }
            if(addBack != null && addBack.occurrences > 1){
                //add the latest unusable occ into the map
                if(map.containsKey(addBack.occurrences - 1)){
                    map.get(addBack.occurrences - 1).add(addBack.num);
                }
                else{
                    TreeSet<Integer> put = new TreeSet<>();
                    put.add(addBack.num);
                    map.put(addBack.occurrences - 1, put);
                }
            }
        }
        return true;
    }
}

class occ implements Comparable<occ>{
    int num, occurrences;
    public occ(int num, int occurrences){
        this.num = num; this.occurrences = occurrences;
    }
    public int compareTo(occ other){
        return other.num - this.num;
    }
}

class sort implements Comparator<occ>{
    public int compare(occ a, occ b){
        return b.occurrences - a.occurrences;
    }
}

class sort1 implements Comparator<Integer>{
    public int compare(Integer a, Integer b){
        return b - a;
    }
}