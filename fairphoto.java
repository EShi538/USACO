import java.io.*;
import java.util.*;
public class fairphoto {
    static int n;
    static List<cow> cows = new ArrayList<>();
    static int[] PS;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("fairphoto.in"));
        PrintWriter out = new PrintWriter(new File("fairphoto.out"));
        n = Integer.parseInt(in.readLine());
        PS = new int[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int pos = Integer.parseInt(st.nextToken());
            char type = st.nextToken().charAt(0);
            cows.add(new cow(pos, type));
        }
        in.close();
        cows.sort(new sort());
        int hCnt = 0, gCnt = 0;
        for(int i = 0; i < n; i++){
            if(cows.get(i).type == 'H'){
                hCnt++;
            }
            else{
                gCnt++;
            }
            PS[i] = hCnt - gCnt;
        }
        Map<Integer, Integer> firstOcc = new HashMap<>();
        Map<Integer, Integer> lastOcc = new HashMap<>();
        firstOcc.put(0, -1);
        for(int i = 0; i < n; i++){
            if(!firstOcc.containsKey(PS[i])){
                firstOcc.put(PS[i], i);
            }
        }
        for(int i = 0; i < n; i++){
            lastOcc.put(PS[i], i);
        }
        int ans = 0;
        for(int i: lastOcc.keySet()){
            int length;
            if(firstOcc.get(i) != n - 1) {
                length = cows.get(lastOcc.get(i)).pos - cows.get(firstOcc.get(i) + 1).pos;
            }
            else{
                length = 0;
            }
            ans = Math.max(length, ans);
        }
        int start = 0;
        char curr = cows.get(0).type;
        for(int i = 0; i < n; i++){
            if(cows.get(i).type != curr){
                ans = Math.max(ans, cows.get(i - 1).pos - cows.get(start).pos);
                start = i;
                curr = cows.get(i).type;
            }
        }
        out.println(ans);
        out.close();
    }
}
class cow{
    int pos;
    char type;
    public cow(int pos, char type){
        this.pos = pos;
        this.type = type;
    }
}
class sort implements Comparator<cow>{
    public int compare(cow a, cow b){
        return a.pos - b.pos;
    }
}