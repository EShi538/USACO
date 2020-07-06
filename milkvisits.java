import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class milkvisits {
    static List<barn> barns = new ArrayList<barn>();
    static List<ArrayList<barn>> adjList = new ArrayList<ArrayList<barn>>();

    public static void floodFill(barn st, int reg, char cow, boolean[] visited){
        visited[st.ID] = true;
        barns.get(st.ID).region = reg;
        ArrayList<barn> adj = adjList.get(st.ID);
        for(int i = 0; i < adj.size(); i++){
            barn nBarn = adj.get(i);
            if(visited[nBarn.ID] == false && nBarn.cow == cow){
                floodFill(nBarn, reg, cow, visited);
            }
        }
        return;
    }

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("milkvisits.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String cows = in.readLine();
        boolean[] visited = new boolean[n + 1];
        barns.add(new barn(-1, '.'));
        for(int i = 1; i <= n; i++){
            barns.add(new barn(i, cows.charAt(i - 1)));
        }
        adjList.add(new ArrayList<barn>());
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<barn>());
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjList.get(x).add(barns.get(y));
            adjList.get(y).add(barns.get(x));
        }
        
        int cnt = 1;
        for(int i = 1; i <= n; i++){
            if(barns.get(i).region == 0){
                floodFill(barns.get(i), cnt, barns.get(i).cow, visited);
                cnt++;
            }
        }

        File out = new File("milkvisits.out");
        FileWriter writer = new FileWriter(out);
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            char cow = st.nextToken().charAt(0);
            if(barns.get(a).region != barns.get(b).region){
                writer.write("1");
            }
            else if(barns.get(a).cow == cow){
                writer.write("1");
            }
            else{
                writer.write("0");
            }
        }
        in.close();
        writer.close();
    }
}

class barn{
    int ID;
    int region;
    char cow;
    public barn(int ID, char cow){
        this.ID = ID;
        this.cow = cow;
        this.region = 0;
    }
}