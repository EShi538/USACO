import java.io.*;
import java.util.*;
public class tlines {
    public static void main(String[] args) throws Exception{
        //FileReader reader = new FileReader("tlines.in");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        List<point> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new point(x, y));
        }
        in.close();

        Set<Integer> checkX = new HashSet<>();
        Set<Integer> checkY = new HashSet<>();
        for(point i: points){
            checkX.add(i.x);
            checkY.add(i.y);
        }
//        File out = new File("tlines.out");
//        FileWriter writer = new FileWriter(out);

        //3H 0V or 0H 3V
        if(checkX.size() <= 3 || checkY.size() <= 3){
            System.out.println(1);
//            writer.write("1");
//            writer.close();
            return;
        }

        //create map
        Map<Integer, TreeSet<Integer>> Vmap = new TreeMap<Integer, TreeSet<Integer>>();
        for(point i: points){
            TreeSet<Integer> put;
            if(!Vmap.containsKey(i.x)){
                put = new TreeSet<>();
            }
            else{
                put = Vmap.get(i.x);
            }
            put.add(i.y);
            Vmap.put(i.x, put);
        }
        Map<Integer, TreeSet<Integer>> Hmap = new TreeMap<Integer, TreeSet<Integer>>();
        for(point i: points){
            TreeSet<Integer> put;
            if(!Hmap.containsKey(i.y)){
                put = new TreeSet<>();
            }
            else{
                put = Hmap.get(i.y);
            }
            put.add(i.x);
            Hmap.put(i.y, put);
        }

        //create todo's
        int[] Htodo = new int[n];
        int[] Vtodo = new int[n];
        for(int i = 0; i < n; i++){
            Htodo[i] = points.get(i).y;
            Vtodo[i] = points.get(i).x;
        }

        //process other cases
        //find max
        int VmaxSize = -1;
        int VmaxKey = -1;
        for(int i: Vmap.keySet()){
            if(Vmap.get(i).size() > VmaxSize){
                VmaxKey = i;
                VmaxSize = Vmap.get(i).size();
            }
        }
        int HmaxSize = -1;
        int HmaxKey = -1;
        for(int i: Hmap.keySet()){
            if(Hmap.get(i).size() > HmaxSize){
                HmaxKey = i;
                HmaxSize = Hmap.get(i).size();
            }
        }

        //process
        //2H 1V
        TreeSet<Integer> bestV = Vmap.get(VmaxKey);
        boolean[] marked = new boolean[n];
        Arrays.sort(Htodo);
        Arrays.sort(Vtodo);
        for(int i: bestV){
            int pos = Arrays.binarySearch(Htodo, i);
            if(pos >= 0) {
                marked[pos] = true;
            }
        }
        Set<Integer> check2 = new HashSet<Integer>();
        for(int i = 0; i < n; i++){
            if(!marked[i]){
                check2.add(Htodo[i]);
            }
        }
        if(check2.size() <= 2){
            System.out.println(1);
//            writer.write("1");
//            writer.close();
            return;
        }

        //1H 2V
        TreeSet<Integer> bestH = Hmap.get(HmaxKey);
        marked = new boolean[n];
        for(int i: bestH){
            int pos = Arrays.binarySearch(Vtodo, i);
            if(pos >= 0) {
                marked[pos] = true;
            }
        }
        check2 = new HashSet<Integer>();
        for(int i = 0; i < n; i++){
            if(!marked[i]){
                check2.add(Vtodo[i]);
            }
        }
        if(check2.size() <= 2){
            System.out.println(1);
//            writer.write("1");
//            writer.close();
            return;
        }
        System.out.println(0);
//        writer.write("0");
//        writer.close();
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