import java.io.*;
import java.util.*;
public class auto {
    public static void main(String[] args)throws Exception{
        FileReader reader = new FileReader("auto.in");
        BufferedReader in = new BufferedReader(reader);
        File out = new File("auto.out");
        PrintWriter writer = new PrintWriter(out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        record[] dict = new record[w];

        for(int i = 0; i < w; i++){
            dict[i] = new record(in.readLine(), i);
        }
        Arrays.sort(dict, new sort());
        List<query> queries = new ArrayList<query>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());
            String partial = st.nextToken();
            queries.add(new query(k, partial));
        }
        in.close();

        for(query i: queries){
            int firstPos = binSearch(dict, i.partial);
            if(firstPos == Integer.MAX_VALUE){
                writer.println(-1);
                continue;
            }
            if(firstPos + i.k - 1 < w && dict[firstPos + i.k - 1].word.startsWith(i.partial)){
                writer.println(dict[firstPos + i.k - 1].index + 1);
            }
            else{
                writer.println(-1);
            }
        }
        writer.close();
    }
    static int binSearch(record[] dict, String target){
        int lb = 0; int ub = dict.length;
        int ans = Integer.MAX_VALUE;
        while(lb != ub){
            int m = (lb + ub)/2;
            if(dict[m].word.length() < target.length()){
                if(dict[m].word.compareTo(target) < 0){
                    lb = m + 1;
                }
                else{
                    ub = m;
                }
                continue;
            }
            if(dict[m].word.length() >= target.length() && dict[m].word.startsWith(target)){
                ub = m;
                ans = Math.min(ans, m);
            }
            else if(dict[m].word.substring(0, target.length()).compareTo(target) < 0){
                lb = m + 1;
            }
            else{
                ub = m;
            }
        }
        return ans;
    }
}

class query{
    int k;
    String partial;
    public query(int k, String partial){
        this.k = k;
        this.partial = partial;
    }
}

class record{
    String word;
    int index;
    public record(String word, int index){
        this.word = word;
        this.index = index;
    }
}

class sort implements Comparator<record>{
    public int compare(record a, record b){
        return a.word.compareTo(b.word);
    }
}