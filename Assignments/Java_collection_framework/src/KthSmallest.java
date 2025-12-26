import java.util.*;

public class KthSmallest {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(7, 2, 9, 4, 1, 5));
        numbers.add(10);
        // value of k
        int k = 3;
        Collections.sort(numbers);

        System.out.println("The " + k + "rd smallest element is: " + numbers.get(k-1));
    }
}