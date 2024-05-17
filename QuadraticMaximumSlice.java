import java.util.*;
import java.util.stream.*;

public class QuadraticMaximumSlice {

    public static void main(String [] args) {
        System.out.printf("Hello Quadratic Maximum Slice solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java QuadraticMaximumSlice%n");
            return;
        }
        int [] A = new int [] {5, -7, 3, 5, -2, 4, -1};
        QuadraticMaximumSlice maximumSlice = new QuadraticMaximumSlice();
        int result = maximumSlice.solution(A);
        System.out.printf("The maximum slice sum is %d%n", result);
    }

    public int solution(int [] A) {
        int result = 0;
        int n = A.length;
        for (int p = 0; p < n; p++) {
            int sum = 0;
            for (int q = p; q < n; q++) {
                sum += A[q];
                result = Math.max(result, sum);
            }
        }
        return result;
    }

}
