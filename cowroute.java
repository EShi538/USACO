import java.io.*;
import java.util.*;
public class cowroute {
    static int a, b, n;
    static List<route> routes = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("cowroute.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        File out = new File("cowroute.out");
        PrintWriter writer = new PrintWriter(out);
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        TreeSet<Integer> allCities = new TreeSet<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            city[] cities = new city[size];
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < size; j++){
                cities[j] = new city(Integer.parseInt(st.nextToken()), j);
                allCities.add(cities[j].ID);
            }
            routes.add(new route(cost, size, cities, i));

        }
        in.close();

        int[][] indexes = new int[n][10001];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 10001; j++){
                indexes[i][j] = -1;
            }
        }
        for(route i: routes){
            for(int j = 0; j < i.cities.length; j++){
                indexes[i.ID][i.cities[j].ID] = j;
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i: allCities){
            if(i == a || i == b){
                continue;
            }
            int firstSectionMin = Integer.MAX_VALUE;
            int firstSectionMinID = Integer.MAX_VALUE;
            for(route j: routes){
                int stPos = indexes[j.ID][a];
                int juncPos = indexes[j.ID][i];
                if((stPos < 0 || juncPos < 0) || (j.cities[stPos].index > j.cities[juncPos].index)){
                    continue;
                }
                if(j.cost < firstSectionMin){
                    firstSectionMin = j.cost;
                    firstSectionMinID = j.ID;
                }
            }
            int secondSectionMin = Integer.MAX_VALUE;
            int secondSectionMinID = Integer.MAX_VALUE;
            for(route j: routes){
                int juncPos = indexes[j.ID][i];
                int endPos = indexes[j.ID][b];
                if(juncPos < 0 || endPos < 0 || j.cities[juncPos].index > j.cities[endPos].index){
                    continue;
                }
                if(j.cost < secondSectionMin){
                    secondSectionMin = j.cost;
                    secondSectionMinID = j.ID;
                }
            }
            if(firstSectionMinID == Integer.MAX_VALUE || secondSectionMinID == Integer.MAX_VALUE){
                continue;
            }
            if(secondSectionMinID == firstSectionMinID){
                ans = Math.min(routes.get(secondSectionMinID).cost, ans);
            }
            else{
                ans = Math.min(routes.get(secondSectionMinID).cost + routes.get(firstSectionMinID).cost, ans);
            }
        }
        if(ans != Integer.MAX_VALUE) {
            writer.println(ans);
        }
        else{
            writer.println(-1);
        }
        writer.close();
    }
}

class route{
    int cost, size, ID;
    city[] cities;
    public route(int cost, int size, city[] cities, int ID){
        this.cost = cost; this.size = size; this.cities = cities; this.ID = ID;
    }
}

class city{
    int ID, index;
    public city(int ID, int index){
        this.ID = ID; this.index = index;
    }
}

class sort implements Comparator<city>{
    public int compare(city a, city b){
        return a.ID - b.ID;
    }
}