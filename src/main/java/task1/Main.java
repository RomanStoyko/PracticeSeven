package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int size = getIntValue(bufferedReader,"Input size:", "[1-9][0-9]*");
        int min = getIntValue(bufferedReader,"Input min boarder:", "[1-9][0-9]*");
        int max = getIntValue(bufferedReader,"Input max boarder:", "[1-9][0-9]*");


        Generator generator = new Generator(size,min,max);
        List<Integer> list = generator.genList();
        for (int i:list) {
            System.out.print(i + ";");
        }
        System.out.println();
        Set<Integer> set = generator.genSet();
        for(int i:set) {
            System.out.print(i + ";");
        }
    }

    public static int getIntValue(BufferedReader br, String message,String reg){
        System.out.println(message);
        String result = null;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher;
        while (true) {
            try {
                result = br.readLine();
                matcher = pattern.matcher(result);
                if (matcher.find()){
                    return Integer.valueOf(result);
                }else{
                    System.out.println("Wrong input! Repeat!");
                }
            } catch (IOException e) {
                System.out.println("Wrong input! Repeat!");
            }
        }

    }
}
