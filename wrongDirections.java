import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    static Scanner in = new Scanner(System.in);
    static String instruction = in.next();
    static int[] x = new int[instruction.length() + 1];
    static int[] y = new int[instruction.length() + 1];
    static int[] dir = new int[instruction.length() + 1];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {
        x[0] = 0;
        y[0] = 0;
        dir[0] = 0;
        //prefix sums
        for(int i = 1; i <= instruction.length(); i++){
            if(instruction.charAt(i - 1) == 'F'){
                x[i] = x[i - 1] + dx[dir[i - 1]];
                y[i] = y[i - 1] + dy[dir[i - 1]];
                dir[i] = dir[i - 1];
            }
            else if(instruction.charAt(i - 1) == 'R'){
                x[i] = x[i - 1];
                y[i] = y[i - 1];
                dir[i] = (dir[i - 1] + 1) % 4;
            }
            else{
                x[i] = x[i - 1];
                y[i] = y[i - 1];
                dir[i] = ((dir[i - 1] + 4) - 1) % 4;
            }
        }
        //transform
        Set <point> ans = new HashSet<point>(); 
        for(int i = 0; i < instruction.length(); i++){
            if(instruction.charAt(i) == 'F'){
                //F -> R
                int newX = x[i] + (y[instruction.length()] - y[i + 1]);
                int newY = y[i] - (x[instruction.length()] - x[i + 1]);
                ans.add(new point(newX, newY));
                //F -> L
                newX = x[i] - (y[instruction.length()] - y[i + 1]);
                newY = y[i] + (x[instruction.length()] - x[i + 1]);
                ans.add(new point(newX, newY));
            }
            else if(instruction.charAt(i) == 'R'){
                //R -> L
                int newX = 2 * x[i + 1] - x[instruction.length()];
                int newY = 2 * y[i + 1] - y[instruction.length()];
                ans.add(new point(newX, newY));
                //R -> F
                newX = x[i + 1] + dx[dir[i]] - (y[instruction.length()] - y[i + 1]);
                newY = y[i + 1] + dy[dir[i]] + (x[instruction.length()] - x[i + 1]);
                ans.add(new point(newX, newY));
            }
            else{
                //L -> R
                int newX = 2 * x[i + 1] - x[instruction.length()];
                int newY = 2 * y[i + 1] - y[instruction.length()];
                ans.add(new point(newX, newY));
                //L -> F
                newX = x[i + 1] + dx[dir[i]] + (y[instruction.length()] - y[i + 1]);
                newY = y[i + 1] + dy[dir[i]] - (x[instruction.length()] - x[i + 1]);
                ans.add(new point(newX, newY));
            }
        }
        System.out.println(ans.size());
    }
}

class point{
    int x;
    int y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof point)){
            return false;
        }
        point p = (point) o;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode(){
        return (100000 * x) + y;
    } 
}