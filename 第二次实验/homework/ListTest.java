import java.io.*;
import java.util.*;

public class ListTest {
    public static void main(String[] args) {
        // 使用ArrayList来保存测试用例
        DoublyLinkedList list = new DoublyLinkedList();

        // 测试用例文件路径
        String testcaseFilePath = "list_testcase.txt";

        // 使用BufferedReader读取文件
        try (BufferedReader testcaseReader = new BufferedReader(new FileReader(testcaseFilePath))) {
            String testcaseLine;

            // 打印输出的PrintWriter
            PrintWriter pw = new PrintWriter(System.out);

            // 逐行读取文件内容
            while ((testcaseLine = testcaseReader.readLine()) != null) {
                // 打印当前行
                //System.out.println("Processing line: " + testcaseLine);

                // 将命令行内容按空格分割
                String[] tokens = testcaseLine.split(" ");
                for (String token : tokens) {
                    if (token.isEmpty()) continue; // 忽略空白字符

                    // 提取命令和参数
                    String command = token.substring(0, 1);
                    String parameter = token.length() > 1 ? token.substring(1) : null;

                    // 判断列表是否为空，如果为空需要跳过某些操作
                    if (list.isEmpty()&& (command.equals("-") || command.equals("=") || command.equals(">") || command.equals("<")|| command.equals("#")|| command.equals("*"))) {
                        continue; // 如果列表为空，跳过该命令
                    }

                    // 根据命令执行操作
                    switch (command) {
                        case "+":
                            try {
                                if (parameter != null && !parameter.isEmpty()) {
                                    char element = parameter.charAt(0);
                                    list.insert(element);
                                }
                            } catch (ListException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case "-":
                            list.remove();
                            break;
                        case "=":
                            if (parameter != null && !parameter.isEmpty()) {
                                list.replace(parameter.charAt(0));
                            }
                            break;
                        case "#":
                            list.gotoBeginning();
                            break;
                        case "*":
                            list.gotoEnd();
                            break;
                        case ">":
                            list.gotoNext();
                            break;
                        case "<":
                            list.gotoPrev();
                            break;
                        case "~":
                            list.clear();
                            break;
                        default:
                            break;
                    }

                }

                // 显示当前列表结构
                list.showStructure(pw);
                pw.flush(); // 确保输出被刷新
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
