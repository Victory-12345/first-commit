import java.util.Stack;

public class Calculator {
    // 检查括号是否匹配
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // 计算表达式的值
    public static double evaluate(String expression) {
        if (!isBalanced(expression)) {
            throw new IllegalArgumentException("括号不匹配");
        }

        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        expression = expression.replaceAll("\\s+", ""); // 移除空格

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sbuf = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sbuf.append(expression.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    values.push(applyOp(values.pop(), values.pop(), ops.pop()));
                }
                ops.pop();
            } else if (isOperator(c)) {
                while (!ops.isEmpty() && hasPrecedence(c, ops.peek())) {
                    values.push(applyOp(values.pop(), values.pop(), ops.pop()));
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            values.push(applyOp(values.pop(), values.pop(), ops.pop()));
        }

        return values.pop();
    }

    // 判断字符是否为运算符
    public static boolean isOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/' || op == '^';
    }

    // 判断运算符的优先级
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/' || op1 == '^') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    // 应用运算符
    public static double applyOp(double b, double a, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("除以0");
                }
                return a / b;
            case '^':
                return Math.pow(a, b);
        }
        return 0;
    }

    public static void main(String[] args) {
        String expression = "3+4*5/(1+1)+2^2";
        try {
            double result = evaluate(expression);
            System.out.println("结果是: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}