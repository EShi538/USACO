/*
ID: origami5
LANG: JAVA
TASK: beads
*/
import java.io.*;
public class beads {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("beads.in");
        BufferedReader in = new BufferedReader(reader);
        int n = Integer.parseInt(in.readLine());
        char[] necklace;
        String neck = in.readLine();
        necklace = neck.toCharArray();
        in.close();

        //breakpoint is to the left of i
        int ans = -1;
        for(int i = 0; i <= n; i++){
            int right = i;
            if(right >= n){
                right = 0;
            }
            int left = i - 1;
            if(left < 0){
                left = n - 1;
            }
            int cnt = 0;
            char st = necklace[right];
            boolean[] visited = new boolean[n];
            if(st != 'w'){
                while(visited[right] == false && (necklace[right] == st || necklace[right] == 'w')){
                    visited[right] = true;
                    cnt++;
                    right++;
                    if(right >= n){
                        right = 0;
                    }
                }
            }
            else{
                boolean[] temp = visited.clone();
                temp[right] = true;
                st = 'r';
                right++;
                int tempCnt = 1;
                if(right >= n){
                    right = 0;
                }
                while(temp[right] == false && (necklace[right] == st || necklace[right] == 'w')){
                    temp[right] = true;
                    tempCnt++;
                    right++;
                    if(right >= n){
                        right = 0;
                    }
                }
                boolean[] temp1 = visited.clone();
                temp1[right] = true;
                st = 'b';
                right = i + 1;
                if(right >= n){
                    right = 0;
                }
                int tempCnt1 = 1;
                while(temp1[right] == false && (necklace[right] == st || necklace[right] == 'w')){
                    temp1[right] = true;
                    tempCnt1++;
                    right++;
                    if(right >= n){
                        right = 0;
                    }
                }
                if(tempCnt > tempCnt1){
                    cnt = cnt + tempCnt;
                    visited = temp.clone();
                }
                else{
                    cnt = cnt + tempCnt1;
                    visited = temp1.clone();
                }
            }
            st = necklace[left];
            if(st != 'w'){
                while(visited[left] == false && (necklace[left] == st || necklace[left] == 'w')){
                    visited[left] = true;
                    cnt++;
                    left--;
                    if(left < 0){
                        left = n - 1;
                    }
                }
            }
            else{
                boolean[] temp = visited.clone();
                st = 'r';
                temp[left] = true;
                left--;
                int tempCnt = 0;
                if(left < 0){
                    left = n - 1;
                }
                if(!temp[left]){
                    tempCnt = 1;
                }
                while(temp[left] == false && (necklace[left] == st || necklace[left] == 'w')){
                    temp[left] = true;
                    tempCnt++;
                    left--;
                    if(left < 0){
                        left = n - 1;
                    }
                }
                boolean[] temp1 = visited.clone();
                temp1[left] = true;
                st = 'b';
                left = i - 2;
                if(left < 0){
                    left = n - 1;
                }
                int tempCnt1 = 0;
                if(!temp[left]){
                    tempCnt = 1;
                }
                while(temp1[left] == false && (necklace[left] == st || necklace[left] == 'w')){
                    temp1[left] = true;
                    tempCnt1++;
                    left--;
                    if(left < 0){
                        left = n - 1;
                    }
                }
                if(tempCnt > tempCnt1){
                    cnt = cnt + tempCnt;
                    visited = temp.clone();
                }
                else{
                    cnt = cnt + tempCnt1;
                    visited = temp1.clone();
                }
            }
            if(cnt > ans){
                ans = cnt;
            }
        }
        File out = new File("beads.out");
        FileWriter writer = new FileWriter(out);
        writer.write(Integer.toString(ans) + "\n");
        writer.close();
    }
}
