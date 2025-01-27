import java.io.*;
import java.util.*;

public class WordLadderInteractive {

    private static Map<String, List<String>> adjList = new HashMap<>();
    private static List<String> wordList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String inputFile = "words5.txt"; // 单词列表文件

        // 1. 读取单词列表并生成邻接表
        readWords(inputFile);
        buildAdjacencyList();

        // 2. 启动交互式字梯游戏
        playGame();
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

    // 启动交互式字梯游戏
    private static void playGame() {
        Scanner scanner = new Scanner(System.in);

        // 随机选择两个单词
        String startWord, endWord;
        do {
            startWord = getRandomWord();
            endWord = getRandomWord();
        } while (!isPathExists(startWord, endWord)); // 确保两单词之间有路径

        System.out.println("字梯游戏，启动!");
        System.out.println("起始单词: " + startWord);
        System.out.println("结束单词: " + endWord);

        // 计算最短路径长度
        int shortestPathLength = findShortestPathLength(startWord, endWord);

        // 游戏交互
        String currentWord = startWord;
        int steps = 0;

        System.out.println("逐个改变字符以到达结束单词。");
        while (true) {
            System.out.print("你的输入: ");
            String userWord = scanner.nextLine().trim();

            // 检查用户输入是否有效
            if (!wordList.contains(userWord)) {
                System.out.println("输入无效！该单词不在字典中，请重新输入。");
                continue;
            }

            if (!differByOne(currentWord, userWord)) {
                // 给出正确的下一个单词
                String nextValidWord = getNextValidWord(currentWord, endWord);
                System.out.println("输入的单词与当前单词相差超过一个字母！下一个有效单词是: " + nextValidWord);
                continue;
            }

            currentWord = userWord;
            steps++;

            if (currentWord.equals(endWord)) {
                System.out.println("恭喜！你成功完成了字梯游戏，步数为 " + steps + " 步。");
                if (steps == shortestPathLength) {
                    System.out.println("太棒了！你找到了最短路径！");
                } else {
                    System.out.println("不过，最短路径是 " + shortestPathLength + " 步。");
                }
                break;
            }
        }

        scanner.close();
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

    // 判断是否存在从 start 到 end 的路径（BFS）
    private static boolean isPathExists(String start, String end) {
        if (start.equals(end)) return true;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String neighbor : adjList.get(current)) {
                if (neighbor.equals(end)) {
                    return true;
                }
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }

    // 计算从 start 到 end 的最短路径长度（BFS）
    private static int findShortestPathLength(String start, String end) {
        if (start.equals(end)) return 0;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                for (String neighbor : adjList.get(current)) {
                    if (neighbor.equals(end)) {
                        return level;
                    }
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return -1; // 无法到达
    }

    // 随机获取一个单词
    private static String getRandomWord() {
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    // 获取与当前单词相差一个字母并且接近目标单词的下一个有效单词
    private static String getNextValidWord(String currentWord, String endWord) {
        // 遍历与当前单词相差一个字母的所有单词
        for (String neighbor : adjList.get(currentWord)) {
            // 返回与目标单词距离最短的那个单词
            if (findShortestPathLength(neighbor, endWord) < findShortestPathLength(currentWord, endWord)) {
                return neighbor;
            }
        }
        return currentWord; // 如果没有找到合适的单词，返回当前单词
    }
}
