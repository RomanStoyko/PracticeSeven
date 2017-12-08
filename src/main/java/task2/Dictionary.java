package task2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Dictionary implements Serializable {

    private HashMap<String,String> lib = new HashMap<String, String>();
    transient private final String determinator = " !@#$%^&*()1234567890?><{}[]";

    public void add(String eng, String ru){
        lib.put(eng.toLowerCase(),ru.toLowerCase());
    }

    public String translate(String eng){

        char[] chars = eng.toCharArray();
        ArrayList<String> stringList = new ArrayList<String>();

        StringBuilder stringBuilder = new StringBuilder("");

        boolean isDeterminator = false;
        for (int i = 0; i < chars.length; i++) {
           if(checkChar(chars[i])){
               isDeterminator = true;
               stringList.add(stringBuilder.toString().toLowerCase());
               stringBuilder = new StringBuilder(String.valueOf(chars[i]));
           }else {
               if(isDeterminator){
                   isDeterminator = false;
                   stringList.add(stringBuilder.toString().toLowerCase());
                   stringBuilder = new StringBuilder(String.valueOf(chars[i]));
               }else {
                   stringBuilder.append(chars[i]);
               }
           }
           if(i == chars.length - 1){
               stringList.add(stringBuilder.toString().toLowerCase());
           }
        }

        stringBuilder = new StringBuilder("");
        for (String engWord:stringList) {

            if(determinator.contains(engWord)
                    || engWord.contains(String.valueOf('\n'))
                    || engWord.contains(String.valueOf('\r'))){
                stringBuilder.append(engWord);
                continue;
            }

            String ruWord  = lib.get(engWord);
            if(ruWord == null){
                ruWord = "null";
            }
            stringBuilder.append(ruWord);
        }

        return stringBuilder.toString() != null ? stringBuilder.toString() : "";
    }

    private boolean checkChar(char c){
        return determinator.contains(String.valueOf(c)) || c == '\r' || c== '\n';
    }
}
