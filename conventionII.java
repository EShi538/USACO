import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Agelada> cows = new ArrayList<Agelada>();    
        for(int i = 0; i < n; i++){
            cows.add(new Agelada(i, in.nextInt(), in.nextInt()));
        }
        in.close();

        Collections.sort(cows, new ArrivalSort());
        PriorityQueue<Agelada> q = new PriorityQueue<Agelada>(new SenioritySort());
        int max = 0;
        Agelada cow = cows.get(0);
        int previousEnd = cows.get(0).arrival;
        int index = 1;
        int end = 0;

        for(int i = 0; i < n; i++){
            end = cow.eatingTime + previousEnd;
            while(index < n && cows.get(index).arrival <= end){
                q.add(cows.get(index));
                index++;
            }
            if(q.size() == 0){
                if(index == n){
                    break;
                }
                cow = cows.get(index);
                index++;
                previousEnd = cow.arrival;
            }
            else{
                previousEnd = end;
                cow = q.poll();
                int wait = previousEnd - cow.arrival;
                if(wait > max){
                    max = wait;
                }
            }
        }
        System.out.println(max);
    }
}

//cow in greek ;)
class Agelada{
    int seniority;
    int arrival;
    int eatingTime;
    public Agelada (int seniority, int arrival, int eatingTime){
        this.seniority = seniority;
        this.arrival = arrival;
        this.eatingTime = eatingTime;
    }
}

class ArrivalSort implements Comparator<Agelada> { 
    public int compare(Agelada a, Agelada b) { 
        return a.arrival - b.arrival; 
    } 
} 
  
class SenioritySort implements Comparator<Agelada>{
    public int compare(Agelada a, Agelada b){
        return a.seniority - b.seniority;
    }
}