import java.util.Stack;

public class QuickSort {

    // 交换两个元素的位置
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 分区操作，返回基准元素的位置
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // 选择最右边的元素作为基准
        int i = low - 1; // i 是小于基准元素的区域的末尾索引
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j); // 将小于等于基准元素的元素移到左侧
            }
        }
        swap(arr, i + 1, high); // 将基准元素交换到正确的位置
        return i + 1; // 返回基准元素的索引
    }

    // 非递归快速排序
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        // 使用栈来模拟递归
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, arr.length - 1}); // 初始区间为 [0, arr.length - 1]

        while (!stack.isEmpty()) {
            // 获取当前区间
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            // 分区操作
            if (low < high) {
                int pivotIndex = partition(arr, low, high); // 获取基准元素的索引

                // 将左右子区间压入栈中
                if (pivotIndex - 1 > low) {
                    stack.push(new int[]{low, pivotIndex - 1});
                }
                if (pivotIndex + 1 < high) {
                    stack.push(new int[]{pivotIndex + 1, high});
                }
            }
        }
    }

    // 打印数组
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        System.out.println("原始数组：");
        printArray(arr);

        quickSort(arr);

        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
