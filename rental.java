import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class rental {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("rental.in");
        BufferedReader in = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] cows = new int[n + 1];
        for(int i = 1; i <= n; i++){
            cows[i] = Integer.parseInt(in.readLine());
        }
        List<shop> shops = new ArrayList<shop>();
        shops.add(new shop(-1, -1));
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(in.readLine());
            shops.add(new shop(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Integer[] farmers = new Integer[r + 1];
        farmers[0] = 0;
        for(int i = 1; i <= r; i++){
            farmers[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        Arrays.sort(cows);
       // Arrays.sort(farmers, Collections.reverseOrder());
        Arrays.sort(farmers);
        Collections.sort(shops, new sortShop());
        long[] farmerPS = new long[r + 1];
        farmerPS[0] = Long.valueOf(0);
        for(int i = 1; i <= r; i++){
            farmerPS[i] = farmerPS[i - 1] + farmers[i];
        }
        long best = -1;
        long[] bestFarmer = new long[n + 2];
        for(int i = 0; i <= n + 1; i++){
            if(i == 0){
                bestFarmer[0] = 0;
                continue;
            }
            else if(i == n + 1){
                bestFarmer[i] = farmerPS[r];
                continue;
            }
            long farmerSum = 0;
            int num = i - 1;
            if(r - i >= 0){
                farmerSum = farmerPS[r] - farmerPS[r - num];
            }
            else{
                farmerSum = farmerPS[r];
            }
            bestFarmer[i] = farmerSum;
        }
        long[] bestShop = new long[n + 2];
        long curr = shops.get(0).limit;
        int index = 0;
        for(int i = n + 1; i >= 0; i--){
            if(i == n + 1){
                bestShop[i] = 0;
                continue;
            }
            long currCow = cows[i];
            long shopSum = 0;
            if(i + 1 <= n){
                shopSum = bestShop[i + 1];
            }
            else{
                shopSum = 0;
            }
            while(currCow > 0 && index < m){
                if(currCow >= curr){
                    shopSum = shopSum + (curr * shops.get(index).ppg);
                    currCow = currCow - curr;
                    index++;
                    curr = shops.get(index).limit;
                }
                else{
                    shopSum = shopSum + (currCow * shops.get(index).ppg);
                    curr = curr - currCow;
                    currCow = 0;
                }
            }
            bestShop[i] = shopSum;
        }
        for(int i = 1; i <= n; i++){
            if(bestFarmer[i] + bestShop[i] > best){
                best = bestShop[i] + bestFarmer[i];
            }
        }
        File out = new File("rental.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Long.toString(best));
        writer.close();
    }

}

class shop{
    int limit;
    int ppg;
    public shop(int limit, int ppg){
        this.limit = limit;
        this.ppg = ppg;
    }
}

class sortShop implements Comparator<shop>{
    public int compare(shop a, shop b){
        return b.ppg - a.ppg;
    }
}