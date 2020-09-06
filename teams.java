import java.io.*;
import java.util.*;
public class teams {
    static int n;
    static List<room> rooms = new ArrayList<>();
    static Set<Integer> teams = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), ID = Integer.parseInt(st.nextToken());
            rooms.add(new room(x, ID));
        }
        in.close();
        rooms.sort(new sort());
        for(room i: rooms){
            teams.add(i.ID);
        }
        int p = 0;
        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> s = new HashMap<>();
        s.put(rooms.get(0).ID, 1);
        for(int i = 0; i < n; i++){
            p = Math.max(i, p);
            if(i !=  0) {
                s.put(rooms.get(i - 1).ID, s.get(rooms.get(i - 1).ID) - 1);
                if (s.get(rooms.get(i - 1).ID) == 0) {
                    s.remove(rooms.get(i - 1).ID);
                }
            }
            boolean looped = false;
            boolean started = true;
            while(p < n && s.size() < teams.size()){
                looped = true;
                if(!started) {
                    if (s.containsKey(rooms.get(p).ID)) {
                        s.put(rooms.get(p).ID, s.get(rooms.get(p).ID) + 1);
                    } else {
                        s.put(rooms.get(p).ID, 1);
                    }
                }
                started = false;
                p++;
            }
            if(looped) {
                p--;
            }
            if (s.size() == teams.size()) {
                ans = Math.min(ans, rooms.get(p).x - rooms.get(i).x);
            }
            //System.out.println(i + " " + p);
        }
        out.println(ans);
        out.close();
    }
}
class room{
    int x, ID;
    public room(int x, int ID){
        this.x = x;
        this.ID = ID;
    }
}
class sort implements Comparator<room>{
    public int compare(room a, room b){
        return a.x - b.x;
    }
}