import java.io.*;
import java.util.*;
public class convention2 {
    static int n;
    static List<cow1> cows = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("convention2.in"));
        n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
            cows.add(new cow1(i + 1, a, t));
        }
        in.close();
        Collections.sort(cows, new sort5());
        PriorityQueue<cow1> pq = new PriorityQueue<>(new sort6());
        int time = cows.get(0).a;
        int index = 0;
        int ans = 0;
        pq.add(cows.get(0));
        while(true){
            
        }
        //FileWriter out = new FileWriter("convention2.out");
        //out.write(Integer.toString(ans));
        //out.close();
    }
}
class cow1{
    int seniority, a, t;
    public cow1(int seniority, int a, int t){
        this.seniority = seniority;
        this.a = a;
        this.t = t;
    }
}
class sort5 implements Comparator<cow1>{
    public int compare(cow1 a, cow1 b){
        if(a.a != b.a){
            return a.a - b.a;
        }
        else{
            return a.seniority - b.seniority;
        }
    }
}
class sort6 implements Comparator<cow1>{
    public int compare(cow1 a, cow1 b){
        return a.seniority - b.seniority;
    }
}