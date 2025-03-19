import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        int sum = 0;

        for (Integer num : numbers) {
            sum += num; // Unboxing automatically happens here
        }

        System.out.println("Sum of integers: " + sum);
    }
}
