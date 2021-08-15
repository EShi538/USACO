//http://www.usaco.org/index.php?page=viewproblem2&cpid=619
import java.util.*;
import java.io.*;
public class balancing {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("balancing.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        List<point1> points = new ArrayList<point1>();
        List<point1> pointsY = new ArrayList<point1>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new point1(x, y));
            pointsY.add(new point1(x, y));
        }
        in.close();

        Collections.sort(points, new xSort());
        Collections.sort(pointsY, new ySort());
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = n;
        int above = 0;
        int below = n;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            List<point1> sameY = new ArrayList<point1>();
            int ind = i;
            while(ind + 1 < n && pointsY.get(ind).y == pointsY.get(ind + 1).y){
                sameY.add(pointsY.get(ind));
                ind++;
            }
            sameY.add(pointsY.get(ind));
            above = above + sameY.size();
            below = below - sameY.size();
            q1 = above;
            q2 = 0;
            q3 = 0;
            q4 = below; 
            for(int j = 0; j < n; j++){
                List<point1> change = new ArrayList<point1>();
                int index = j;
                while(index + 1 < n && points.get(index).x == points.get(index + 1).x){
                    change.add(points.get(index));
                    index++;
                }
                j = index;
                change.add(points.get(index));
                for(int e = 0; e < change.size(); e++){
                    if(change.get(e).y >= pointsY.get(i).y){
                        q1 = q1 - 1;
                        q2 = q2 + 1;
                    }
                    else{
                        q3 = q3 + 1;
                        q4 = q4 - 1;
                    }
                }
                List<Integer> lst = new ArrayList<Integer>();
                lst.add(q1); lst.add(q2); lst.add(q3); lst.add(q4);
                int max = Collections.max(lst);
                if(max < ans){
                    ans = max;
                }
            }
            i = ind;
        }
        File out = new File("balancing.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }  
}

class point1{
    int x;
    int y;
    public point1(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class xSort implements Comparator<point1>{
    public int compare(point1 a, point1 b){
        if(a.x != b.x){
            return a.x - b.x;
        }
        else{
            return a.y - b.y;
        }
    }
}

class ySort implements Comparator<point1>{
    public int compare(point1 a, point1 b){
        return b.y - a.y;
    }
}