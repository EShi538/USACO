import java.io.*;
import java.util.*;
public class encrypt{
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String word = in.readLine();
        int index = 0;
        List<Character> nums = new ArrayList<Character>();
        while(index < word.length() && Character.isDigit(word.charAt(index)) && word.charAt(index) != '0'){
            nums.add(word.charAt(index));
            index++;
        }
        int numIndex = nums.size() - 1;
        StringBuffer ans = new StringBuffer("");
        for(int i = index; i < word.length(); i++){
            if(word.charAt(i) == '*'){
                ans = new StringBuffer(ans.substring(0, ans.length() - 2)); //O(1)
                ans.append(Character.toString(word.charAt(i - 1))); //O(1)
                ans.append(Character.toString(word.charAt(i - 2))); //O(1)
            }
            else if(word.charAt(i) == '0'){
                ans.append(nums.get(numIndex)); //O(1)
                numIndex--; //O(1)
            }
            else{
                ans.append(Character.toString(word.charAt(i))); //O(1)
            }
        }
        System.out.println(ans);
    }
}