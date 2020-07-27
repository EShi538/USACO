import java.io.*;
import java.util.*;
public class photo {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("photo.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<relationship> relationships = new ArrayList<>();
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a > b){
                int temp = a;
                a = b;
                b = temp;
            }
            relationships.add(new relationship(a, b));
        }
        TreeSet<relationship> set = new TreeSet<>(relationships);
        in.close();

        int cnt = 0;
        int currCow = 0;
        while(currCow <= n){
            cnt++;
            TreeSet<relationship> curr = (TreeSet<relationship>)set.tailSet(new relationship(currCow, -1), true);
            int min = n + 1;
            for(relationship j: curr){
                min = Math.min(j.b, min);
            }
            currCow = min;
        }
        File out = new File("photo.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(cnt));
        writer.close();
    }
}

class relationship implements Comparable<relationship>{
    int a;
    int b;
    public relationship(int a, int b){
        this.a = a;
        this.b = b;
    }
    @Override
    public int compareTo(relationship other){
        return this.a - other.a;
    }
}