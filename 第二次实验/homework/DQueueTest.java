import java.util.Random;
import java.util.Scanner;

public class DQueueTest {

    public static void main(String[] args) {
        // 创建随机数生成器
        Random random = new Random();

        // 生成10组数据，每组数据长度在10到20之间，数组元素值在1到100之间
        int numSets = 10;
        int[][] numsSets = new int[numSets][];

        for (int i = 0; i < numSets; i++) {
            int length = random.nextInt(11) + 10; // 随机长度在10到20之间
            numsSets[i] = new int[length];
            for (int j = 0; j < length; j++) {
                numsSets[i][j] = random.nextInt(100) + 1; // 随机元素值在1到100之间
            }
        }

        // 输出生成的10组数据
        System.out.println("随机生成的10组数据：");
        for (int i = 0; i < numSets; i++) {
            System.out.print("组 " + (i + 1) + ": ");
            for (int num : numsSets[i]) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        // 创建Scanner对象，获取用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入连续的子序列的大小 k: ");
        int k = scanner.nextInt();

        // 逐组处理数据
        for (int i = 0; i < numSets; i++) {
            // 在每组数据开始时重新初始化DQueueLinkedList对象
            DQueueLinkedList dQueue = new DQueueLinkedList();
            System.out.println("对于组 " + (i + 1) + ",每"+k+"个连续的子序列的最大值：");
            int[] result = slidingWindowMax(numsSets[i], k, dQueue);
            for (int value : result) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        scanner.close();  // 关闭Scanner
    }

    public static int[] slidingWindowMax(int[] nums, int k, DQueueLinkedList dQueue) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;

        // 遍历每个元素，模拟滑动窗口
        for (int i = 0; i < n; i++) {
            // 1. 移除队列中已经滑出窗口的元素
            while (!dQueue.isEmpty() && dQueue.getFront() < i - k + 1) {
                dQueue.dequeueFromFront();
            }

            // 2. 移除队列中所有小于当前元素的元素，因为它们不可能成为最大值
            while (!dQueue.isEmpty() && nums[dQueue.getRear()] <= nums[i]) {
                dQueue.dequeueFromRear();
            }

            // 3. 将当前元素的索引入队
            try {
                dQueue.enqueueToRear(i);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }

            // 4. 当窗口滑动到大小k时，记录最大值（队头的元素）
            if (i >= k - 1) {
                result[index++] = nums[dQueue.getFront()];
            }
        }

        return result;
    }
}
