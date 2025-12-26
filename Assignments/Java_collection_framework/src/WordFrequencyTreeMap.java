
import java.util.TreeMap;

public class WordFrequencyTreeMap {
    public static void main(String[] args) {
        String msg= "I am learning Java. Java is fun. Java is a good Programming language";
        String[] words= msg.split(" ");
        TreeMap<String, Integer> tmap = new TreeMap<>();
        for(String st: words){
            tmap.putIfAbsent(st, 0);
            if(tmap.get(st)>=0){
                tmap.compute(st, (k, x) -> x + 1);
            }
        }

        System.out.println("Word frequency: "+ tmap);
    }
}
