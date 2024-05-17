import java.util.*;
import java.util.stream.*;

public class KadaneMaximumSlice {

    public static void main(String [] args) {
        System.out.printf("Hello Kadane Maximum Slice solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java KadaneMaximumSlice%n");
            return;
        }
        int [] A = new int [] {5, -7, 3, 5, -2, 4, -1};
        KadaneMaximumSlice maximumSlice = new KadaneMaximumSlice();
        int result = maximumSlice.solution(A);
        System.out.printf("The maximum slice sum is %d%n", result);
    }

    public int solution(int [] A) {
        int max_ending = 0;
        int max_slice = 0;
        for (int i = 0; i < A.length; i++) {
            max_ending = Math.max(0, max_ending + A[i]);
            max_slice = Math.max(max_slice, max_ending);
        }
        return max_slice;
    }

}
