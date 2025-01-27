import java.io.*;
import java.util.*;

// 自定义LinkedList实现，用于存储行号
class LinkedList {
    private Node head;

    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // 添加行号，避免重复添加同一行号
    public void add(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }
        Node current = head;
        while (current.next != null) {
            if (current.data == data) return; // 避免重复添加
            current = current.next;
        }
        if (current.data != data) current.next = new Node(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }
}

// BST节点类
class BSTNode<K extends Comparable<K>> {
    K key;
    LinkedList lines; // 存储出现的行号
    BSTNode<K> left, right;

    BSTNode(K key, int line) {
        this.key = key;
        this.lines = new LinkedList();
        this.lines.add(line);
        this.left = this.right = null;
    }
}

// BST实现类
class BinarySearchTree<K extends Comparable<K>> {
    private BSTNode<K> root;

    // 插入单词和行号
    public void insert(K key, int line) {
        root = insertRecursive(root, key, line);
    }

    private BSTNode<K> insertRecursive(BSTNode<K> node, K key, int line) {
        if (node == null) return new BSTNode<>(key, line);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insertRecursive(node.left, key, line);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, key, line);
        } else {
            node.lines.add(line); // 单词已存在，更新行号
        }
        return node;
    }

    // 中序遍历，写入文件
    public void printInOrder(PrintWriter pw) {
        printInOrderRecursive(root, pw);
    }

    private void printInOrderRecursive(BSTNode<K> node, PrintWriter pw) {
        if (node == null) return;
        printInOrderRecursive(node.left, pw);
        pw.println("[" + node.key + "---<" + node.lines + ">]");
        printInOrderRecursive(node.right, pw);
    }
}

// 主类
public class IndexBuilder {
    public static void main(String[] args) {
        String inputFile = "article.txt";
        String outputFile = "index_result.txt";

        // 初始化BST
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        // 读取文件并构建索引
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] words = line.split("\\W+"); // 使用非字母字符分割
                for (String word : words) {
                    if (!word.isEmpty()) {
                        bst.insert(word.toLowerCase(), lineNumber); // 单词转小写
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // 输出索引结果到文件
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {
            bst.printInOrder(pw);
            System.out.println("Index successfully written to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}

