import java.util.*;
import java.io.*;
public class assign {
    static char[] perm;
    static List<relationship> relationships = new ArrayList<>();
    static int ans = 0;
    static void gen(int index, int n){
        if(index > n){
            if(check()){
                ans++;
            }
            return;
        }
        perm[index] = 'G';
        gen(index + 1, n);
        perm[index] = 'H';
        gen(index+ 1, n);
        perm[index] = 'J';
        gen(index + 1, n);
    }
    static boolean check(){
        for(relationship i: relationships){
            if(i.type == 'S'){
                if(perm[i.a] != perm[i.b]){
                    return false;
                }
            }
            else{
                if(perm[i.a] == perm[i.b]){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("assign.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        perm = new char[n + 1];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(in.readLine());
            char type = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relationships.add(new relationship(type, a, b));
        }
        in.close();
        gen(1, n);
        File out = new File("assign.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans));
        writer.close();
    }
}

class relationship{
    char type;
    int a;
    int b;
    public relationship(char type, int a, int b){
        this.type = type;
        this.a = a;
        this.b = b;
    }
}