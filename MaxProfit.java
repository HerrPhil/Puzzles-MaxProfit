import java.util.*;
import java.util.stream.*;

public class MaxProfit {

    public static void main(String [] args) {
        System.out.printf("Hello Maximum Profit solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java MaxProfit%n");
            return;
        }
//        int [] A = null;
//        int [] A = new int [0];
//        int [] A = new int [] {23171};
//        int [] A = new int [] {23171, 23171};
        int [] A = new int [] {23171, 21011, 21123, 21366, 21013, 21367};
        MaxProfit maximumProfit = new MaxProfit();
        int result = maximumProfit.solution(A);
        System.out.printf("The maximum profit possible is %d%n", result);
    }

    public int solution(int [] A) {
        if (A == null) return 0; // impossible to gain profit
        if (A.length == 0) return 0; // impossible to gain profit
        if (A.length == 1) return 0; // impossible to gain profit
        if (A.length == 2 && A[0] == A[1]) return 0; // impossible to gain profit

        // Otherwise, analyse for maximum profit

        int minStockPrice = A[0];
        int maxStockPrice = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] < minStockPrice) minStockPrice = A[i];
            // if the previous max price is more than the min price
            // then the max price is reset to the min price,
            if (minStockPrice < maxStockPrice) maxStockPrice = minStockPrice;
            // and look for new max price in a future value.
            if (A[i] > maxStockPrice) maxStockPrice = A[i];
        }

        int result = maxStockPrice - minStockPrice;

        if (result < 0) result = 0;

        return result;
    }

}
