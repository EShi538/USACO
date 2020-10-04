import java.io.*;
import java.util.*;
public class lines {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        List<point> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            points.add(new point(x, y));
        }
        in.close();
        Set<frac> slopes = new HashSet<>();
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(points.get(i).x == points.get(j).x){
                    slopes.add(new frac(Integer.MAX_VALUE, 0));
                }
                int a = points.get(i).y - points.get(j).y, b = points.get(i).x - points.get(j).x;
                slopes.add(new frac(a, b));
            }
        }
        out.println(slopes.size());
        out.close();
    }
}
class point{
    int x, y;
    public point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class frac{
    int a, b;
    public frac(int a, int b){
        int gcd = findGCD(a, b);
        this.a = a/gcd;
        this.b = b/gcd;
    }
    public int findGCD(int number1, int number2) {
        if(number2 == 0) {
            return number1;
        }
        return findGCD(number2, number1%number2);
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof frac)){
            return false;
        }
        frac f = (frac) o;
        return f.a == this.a && f.b == this.b;
    }
    @Override
    public int hashCode(){
        return (10 * a) + b;
    }
}
