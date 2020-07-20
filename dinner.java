import java.io.*;
import java.util.*;
public class dinner {
    public static void main(String[] args) throws Exception{
        //input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<point> cows = new ArrayList<point>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            cows.add(new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        List<point> tables = new ArrayList<point>();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            tables.add(new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        in.close();

        //map each table to closest cows in descending orders
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for(int i = 0; i < m; i++){
            map.put(i, new ArrayList<Integer>());
        }
        for(int i = 0; i < m; i++){
            List<distance> dists = new ArrayList<distance>();
            point tablePos = tables.get(i);
            for(int j = 0; j < n; j++){
                point cowPos = cows.get(j);
                double dist = Math.sqrt(Math.pow(cowPos.x - tablePos.x, 2) + Math.pow(cowPos.y - tablePos.y, 2));
                dists.add(new distance(dist, j));
            }
            Collections.sort(dists, new sortDist());
            ArrayList<Integer> put = map.get(i);
            for(distance j: dists){
                put.add(j.cowID);
            }
            map.put(i, put);
        }

        //simulate
        boolean[] visited = new boolean[n + 1];
        for(int i = 0; i < m; i++){
            List<Integer> best = map.get(i);
            int index = 0;
            while(visited[best.get(index)]){
                index++;
            }
            visited[best.get(index)] = true;
        }
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                System.out.println(i + 1);
                cnt++;
            }
        }
        if(cnt == 0){
            System.out.println(0);
        }
    }
}

class point{
    int x;
    int y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class distance{
    double dist;
    int cowID;
    public distance(double dist, int cowID){
        this.dist = dist;
        this.cowID = cowID;
    }
}

class sortDist implements Comparator<distance>{
    public int compare(distance a, distance b){
        if(a.dist < b.dist){
            return -1;
        }
        else if(a.dist > b.dist){
            return 1;
        }
        else{
            return 0;
        }
    }
}