import java.io.*;
import java.util.*;

public class WordLadderGenerator {

    // 图的邻接表
    private static Map<String, List<String>> adjList = new HashMap<>();
    private static List<String> wordList = new ArrayList<>();

    public static void main(String[] args) {
        // 给定的输入和输出文件名
        String inputFile = "words5.txt"; // 提供的单词列表文件
        String outputFile = "noladder.txt"; // 输出无字梯单词的文件

        try {
            // 1. 读取单词列表并生成邻接表
            readWords(inputFile);
            buildAdjacencyList();

            // 2. 生成无字梯单词文件
            generateNoLadderWords(outputFile);
            System.out.println("Generated noladder.txt with words that cannot form a word ladder.");

        } catch (IOException e) {
            System.out.println("An error occurred while processing the files: " + e.getMessage());
        }
    }

    // 读取单词列表
    private static void readWords(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            wordList.add(line.trim());
        }
        br.close();
    }

    // 构建邻接表
    private static void buildAdjacencyList() {
        for (String word1 : wordList) {
            adjList.putIfAbsent(word1, new ArrayList<>());
            for (String word2 : wordList) {
                if (differByOne(word1, word2)) {
                    adjList.get(word1).add(word2);
                }
            }
        }
    }

    // 生成 noladder.txt 文件
    private static void generateNoLadderWords(String outputFile) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

        for (String word : wordList) {
            if (adjList.get(word).isEmpty()) { // 如果该单词没有邻居，则写入文件
                bw.write(word);
                bw.newLine();
            }
        }

        bw.close();
    }

    // 检查两个单词是否仅相差一个字母
    private static boolean differByOne(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) return false;
            }
        }
        return diffCount == 1;
    }
}
