import java.io.*;
import java.util.*;
public class toyshop {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        List<product> products = new ArrayList<product>();
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int joy = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            products.add(new product(price, joy, i + 1));
        }
        in.close();

        products.sort(new sort());
        List<product> shouldBuy = new ArrayList<product>();
        shouldBuy.add(products.get(0)); shouldBuy.add(products.get(1)); shouldBuy.add(products.get(2));
        shouldBuy.sort(new sort());
        int ans = shouldBuy.get(0).price + shouldBuy.get(1).price + shouldBuy.get(2).price;
        System.out.println(ans);
        for(product i: shouldBuy){
            System.out.println(i.index);
        }
    }
}

class product{
    int index;
    int price;
    int joy;
    public product(int price, int joy, int index){
        this.price = price;
        this.joy = joy;
        this.index = index;
    }
}

class sort implements Comparator<product>{
    public int compare(product a, product b){
        if((float)b.joy/b.price > (float)a.joy/a.price){
            return 1;
        }
        else if((float)b.joy/b.price < (float)a.joy/a.price){
            return -1;
        }
        return 0;
    }
}