import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class RadixSort1 {

    // 自定义队列类，模拟桶的行为
    static class MyQueue {
        Queue<Integer> queue = new LinkedList<>();

        void enqueue(int item) {
            queue.add(item);
        }

        int dequeue() {
            return queue.poll();
        }

        boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // 基数排序的主函数（整数版）
    public static int[] radixSortIntegers(int[] arr) {
        int maxNum = getMax(arr); // 获取数组中的最大值
        int exp = 1; // 初始位数为个位

        // 直到最大数的位数处理完
        while (maxNum / exp > 0) {
            // 创建10个桶（0-9）
            MyQueue[] buckets = new MyQueue[10];
            for (int i = 0; i < 10; i++) {
                buckets[i] = new MyQueue();
            }

            // 将元素放入桶中，按照当前位数的值来决定桶的位置
            for (int num : arr) {
                int bucketIndex = (num / exp) % 10;  // 获取当前位的数字
                buckets[bucketIndex].enqueue(num);
            }

            // 从桶中取出元素，按桶的顺序放回数组
            int idx = 0;
            for (MyQueue bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[idx++] = bucket.dequeue();
                }
            }

            // 移动到下一个位
            exp *= 10;
        }

        return arr;
    }

    // 获取数组中的最大值，用于确定排序的位数
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // 从文件中逐行读取数据
    public static List<int[]> readFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        List<int[]> lines = new ArrayList<>();

        // 逐行读取文件中的数据
        while ((line = br.readLine()) != null) {
            // 按空格分割每行的数字
            String[] tokens = line.split("\\s+");
            int[] nums = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }
            lines.add(nums);  // 将每行的数字数组添加到列表中
        }
        br.close();
        return lines;
    }

    // 将排序结果写入文件
    public static void writeToFile(String fileName, List<int[]> sortedLines) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        // 将每行的排序结果写入文件
        for (int[] arr : sortedLines) {
            for (int i = 0; i < arr.length; i++) {
                bw.write(arr[i] + (i < arr.length - 1 ? " " : ""));
            }
            bw.newLine();
        }
        bw.close();
    }

    public static void main(String[] args) {
        try {
            // 读取输入文件
            List<int[]> lines = readFromFile("radixSort1.txt");

            // 对每一行数据进行排序
            List<int[]> sortedLines = new ArrayList<>();
            for (int[] line : lines) {
                int[] sortedLine = radixSortIntegers(line);
                sortedLines.add(sortedLine);
            }

            // 输出排序结果到文件
            writeToFile("output.txt", sortedLines);

            System.out.println("Sorting complete. Results written to 'output.txt'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
