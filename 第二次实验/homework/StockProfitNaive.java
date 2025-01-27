public class StockProfitNaive {

    public static int findMaxValueCount(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;  // 如果数组为空，返回 0
        }

        int maxCount = 0;  // 用于记录最多小于元素的个数
        int resultCount = 0;  // 用于记录结果的区间长度

        // 遍历每个元素
        for (int i = 1; i < n; i++) {
            int count = 0;

            // 计算 prices[i] 前面比它小的元素个数
            for (int j = 0; j < i; j++) {
                if (prices[j] < prices[i]) {
                    count++;
                }
            }

            // 如果当前元素前面比它小的个数更多，更新最大值
            if (count > maxCount) {
                maxCount = count;
                resultCount = maxCount + 1;  // 包括当前元素
            } else if (count == maxCount) {
                // 如果当前元素前面比它小的个数与最大值相等，更新区间长度
                if (maxCount + 1 > resultCount) {
                    resultCount = maxCount + 1;
                }
            }
        }

        return resultCount;
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("最大上涨区间的长度: " + findMaxValueCount(prices));
    }
    }

