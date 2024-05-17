import java.util.*;
import java.util.stream.*;

public class NaiveMaximumSlice {

    public static void main(String [] args) {
        System.out.printf("Hello Naive Maximum Slice solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java NaiveMaximumSlice%n");
            return;
        }
        int [] A = new int [] {5, -7, 3, 5, -2, 4, -1};
        NaiveMaximumSlice maximumSlice = new NaiveMaximumSlice();
        int result = maximumSlice.solution(A);
        System.out.printf("The maximum slice sum is %d%n", result);
    }

    public int solution(int [] A) {
        int result = 0;
        int n = A.length;
        for (int p = 0; p < n; p++) {
            for (int q = p; q < n; q++) {
                int sum = 0;
                for (int i = p; i < q; i++) {
                    sum += A[i];
                }
                result = Math.max(result, sum);
            }
        }
        return result;
    }

}
