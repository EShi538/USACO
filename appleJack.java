import java.io.*;
import java.util.*;
public class appleJack {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        TreeMap<Integer, Integer> sizes = new TreeMap<>();
        for(int i = 0; i < n; i++){
            int length = Integer.parseInt(st.nextToken());
            if(!sizes.containsKey(length)){
                sizes.put(length, 1);
            }
            else{
                sizes.put(length, sizes.get(length) + 1);
            }
        }
        TreeSet<Integer> pair = new TreeSet<>(), quad = new TreeSet<>(), many = new TreeSet<>();
        for(int i: sizes.keySet()){
            if(sizes.get(i) > 1 && sizes.get(i) < 4){
                pair.add(i);
            }
            else if(sizes.get(i) > 1 && sizes.get(i) < 8){
                pair.add(i); quad.add(i);
            }
            else if(sizes.get(i) > 7){
                pair.add(i); quad.add(i); many.add(i);
            }
        }
        int q = Integer.parseInt(in.readLine());
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(in.readLine());
            char type = st.nextToken().charAt(0);
            int amount = Integer.parseInt(st.nextToken());
            if(type == '+'){
                if(!sizes.containsKey(amount)){
                    sizes.put(amount, 1);
                }
                else{
                    sizes.put(amount, sizes.get(amount) + 1);
                    if(sizes.get(amount) > 1 && sizes.get(amount) < 4){
                        pair.add(amount);
                    }
                    else if(sizes.get(amount) > 1 && sizes.get(amount) < 8){
                        pair.add(amount); quad.add(amount);
                    }
                    else if(sizes.get(amount) > 7){
                        pair.add(amount); quad.add(amount); many.add(amount);
                    }
                }
            }
            else{
                if(sizes.get(amount) == 1){
                    sizes.remove(amount);
                    pair.remove(amount); quad.remove(amount); many.remove(amount);
                }
                else if(sizes.get(amount) == 2){
                    pair.remove(amount); quad.remove(amount); many.remove(amount);
                    sizes.put(amount, sizes.get(amount) - 1);
                }
                else if(sizes.get(amount) == 4){
                    quad.remove(amount); many.remove(amount);
                    sizes.put(amount, sizes.get(amount) - 1);
                }
                else if(sizes.get(amount) == 8){
                    many.remove(amount);
                    sizes.put(amount, sizes.get(amount) - 1);

                }
                else{
                    sizes.put(amount, sizes.get(amount) - 1);
                }
            }
//            System.out.println();
//            System.out.println("CASE: " + type + amount);
//            System.out.println(sizes);
//            System.out.print("PAIR: ");
//            for(int j: pair){
//                System.out.print(j + " ");
//            }
//            System.out.println();
//            System.out.print("QUAD: ");
//            for(int j: quad){
//                System.out.print(j + " ");
//            }
//            System.out.println();
//            System.out.print("MANY: ");
//            for(int j: many){
//                System.out.print(j + " ");
//            }
//            System.out.println();
//            System.out.print("RESULT: ");
            if(many.size() > 0){
                System.out.println("YES");
                continue;
            }
            if(quad.size() > 1){
                System.out.println("YES");
                continue;
            }
            if(quad.size() == 1){
                int num = sizes.get(quad.first());
                if(num - 4 < 2 && pair.size() > 2){
                    System.out.println("YES");
                    continue;
                }
                if(num - 4 > 1 && pair.size() > 1){
                    System.out.println("YES");
                    continue;
                }
            }
            System.out.println("NO");
        }
        writer.close();
    }
}
