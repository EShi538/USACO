import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String magazine = in.next();
        String badWord = in.next();
        in.close();

        String build = "";
        int index = 0;
        int index1 = 0;
        while(true){
            build += magazine.charAt(index);
            if(index1 == badWord.length() - 1){
                if(build.substring(0, index1 + 1).equals(badWord)){
                    StringBuilder str = new StringBuilder(build);
                    str.delete(0, index1);
                    build = str.toString();
                    index1 = index1 - badWord.length() + 1;
                    continue;
                }
            }
            if(index < badWord.length()){
                index++;
                index1++;
                continue;
            }
            if(build.substring(index1 - badWord.length() + 1, index1 + 1).equals(badWord)){
                StringBuilder str = new StringBuilder(build);
                str.delete(index1 - badWord.length() + 1, index1 + 1);
                build = str.toString();
                index1 = index1 - badWord.length();
            }
            if(index == magazine.length() - 1){
                break;
            }
            index1++;
            index++;
        }
        System.out.print(build);
    }
}
