package task2;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Dictionary dictionary = null;

        File file = new File( System.getProperty("user.dir")+ "\\" +"dictionary.ser");
        try {
            if (!file.exists()) {
                file.createNewFile();
                dictionary = new Dictionary();
            }else{
                try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    dictionary = (Dictionary) ois.readObject();

                }catch (EOFException e){
                        dictionary = new Dictionary();
                }
            }
        } catch (ClassNotFoundException| IOException e) {
            System.err.println("read/create error:" + e.getMessage() );

        }

        String result = null;

        while (true){
            printMenu();
            try {
                result = bufferedReader.readLine();
            } catch (IOException e) {
                System.out.println("Wrong input! Repeat!");
                continue;
            }

            if(result.equals("-ext")){
                if(file.exists()){
                    try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
                        oos.writeObject(dictionary);
                    } catch ( IOException e) {
                        System.err.println("write error:" + e.getMessage() );

                    }
                }
                break;}

            if(result.equals("-add")){
                String eng = getStringValue(bufferedReader,"Input english word:", "[a-zA-Z][a-zA-Z]*");
                String ru = getStringValue(bufferedReader,"Input ru word:", "[а-яА-Я][а-яА-Я]*");
                dictionary.add(eng,ru);
                continue;}
            if(result.equals("-trl")){
                String eng = getStringValue(bufferedReader,"Input some text", "");

                String ru = dictionary.translate(eng);
                if(ru != null){
                    System.out.println(ru);
                }else{
                    System.out.println("No result");
                }

            }
        }


    }

    public static String getStringValue(BufferedReader br, String message,String reg){
        System.out.println(message);
        String result = null;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher;
        while (true) {
            try {
                result = br.readLine();
                matcher = pattern.matcher(result);
                if (matcher.find()){
                    return result;
                }else{
                    System.out.println("Wrong input! Repeat!");
                }
            } catch (IOException e) {
                System.out.println("Wrong input! Repeat!");
            }
        }

    }

    private static void printMenu(){
        System.out.println("Exit -ext");
        System.out.println("add word -add");
        System.out.println("translate -trl");
    }
}
