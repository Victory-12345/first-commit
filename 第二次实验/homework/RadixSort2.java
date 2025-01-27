import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class RadixSort2 {

    // 自定义队列类，模拟桶的行为
    static class MyQueue {
        Queue<String> queue = new LinkedList<>();

        void enqueue(String item) {
            queue.add(item);
        }

        String dequeue() {
            return queue.poll();
        }

        boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // 基数排序的主函数（字符串版）
    public static String[] radixSortStrings(String[] arr, int strLength) {
        int exp = strLength - 1; // 从字符串的最后一位开始排序

        // 直到所有字符位都处理完
        while (exp >= 0) {
            // 创建256个桶，假设字符集为ASCII码范围（0-255）
            MyQueue[] buckets = new MyQueue[256];  // 256个桶对应所有ASCII字符
            for (int i = 0; i < 256; i++) {
                buckets[i] = new MyQueue();
            }

            // 将字符串按照当前位的字符放入桶中
            for (String str : arr) {
                char currentChar = str.charAt(exp);
                buckets[currentChar].enqueue(str);
            }

            // 从桶中取出元素，按桶的顺序放回数组
            int idx = 0;
            for (MyQueue bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[idx++] = bucket.dequeue();
                }
            }

            // 移动到下一个字符位置
            exp--;
        }

        return arr;
    }

    // 从文件中逐行读取数据
    public static List<String[]> readFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        List<String[]> lines = new ArrayList<>();

        // 逐行读取文件中的数据
        while ((line = br.readLine()) != null) {
            // 按空格分割每行的字符串
            String[] tokens = line.split("\\s+");
            lines.add(tokens);  // 将每行的字符串数组添加到列表中
        }
        br.close();
        return lines;
    }

    // 将排序结果写入文件
    public static void writeToFile(String fileName, List<String[]> sortedLines) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        // 将每行的排序结果写入文件
        for (String[] arr : sortedLines) {
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
            List<String[]> lines = readFromFile("radixSort2.txt");

            // 对每一行数据进行排序
            List<String[]> sortedLines = new ArrayList<>();
            for (String[] line : lines) {
                // 假设每个字符串长度相同，取第一个字符串的长度来确定
                String[] sortedLine = radixSortStrings(line, line[0].length());
                sortedLines.add(sortedLine);
            }

            // 输出排序结果到文件
            writeToFile("output2.txt", sortedLines);

            System.out.println("Sorting complete. Results written to 'output.txt'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
