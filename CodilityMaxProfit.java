import java.util.*;
import java.util.stream.*;

public class CodilityMaxProfit {

    public static void main(String [] args) {
        System.out.printf("Hello Maximum Profit solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java CodilityMaxProfit%n");
            return;
        }
//        int [] A = null;
//        int [] A = new int [0];
//        int [] A = new int [] {23171};
//        int [] A = new int [] {23171, 23171};
        int [] A = new int [] {23171, 21011, 21123, 21366, 21013, 21367};
        CodilityMaxProfit maximumProfit = new CodilityMaxProfit();
        int result = maximumProfit.solution(A);
        System.out.printf("The maximum profit possible is %d%n", result);
    }

    public int solution(int [] A) {

        if (A == null) return 0;
        if (A.length <= 1) return 0; 

        int minStockPrice = A[0];
        int maxProfit = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] < minStockPrice) {
                minStockPrice = A[i];
            } else {
                int curProfit = A[i] - minStockPrice;
                if (curProfit > maxProfit) maxProfit = curProfit;
            }
        }

        return maxProfit;
    }

}
