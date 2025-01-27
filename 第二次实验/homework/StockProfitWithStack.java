import java.util.Stack;

public class StockProfitWithStack {


        public static int findMaxValueCount(int[] prices) {
            int n = prices.length;
            if (n == 0) {
                return 0;  // 如果数组为空，返回 0
            }

            Stack<int[]> stack = new Stack<>();
            int resultCount = 0;
            for (int i = 0; i < n; i++) {
                int count = 1;
                   while (!stack.isEmpty() && stack.peek()[0] < prices[i]) {
                       count+=stack.peek()[1];
                       stack.pop();
                   }
                resultCount =count;
                stack.push(new int[]{prices[i], count});
            }

            return resultCount;
        }

        public static void main(String[] args) {
            int[] prices = {100, 80, 60, 70, 60, 75, 85};
            System.out.println("最大上涨区间的长度: " + findMaxValueCount(prices));
        }
}

