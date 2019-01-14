package hj.action.stack.expression;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * <p>Title : 表达式计算</p>
 * <p>Description :
 * 数学表达式：中缀表达式
 * 后缀表达式：便于计算机运算
 * <p>
 * 1、将中缀表达式转换为后缀表达式
 * 2、计算后缀表达式
 * <p>
 * </p>
 * <p>Date : 2018/12/7 </p>
 *
 * @author : hejie
 */
public class Eval {
    //运算优先级
    private final HashMap<String, Integer> level = new HashMap<>();
    /**
     * 运算数据，默认为数字
     */
    private final Pattern dataPattern;

    /**
     * 操作符
     * 定义、优先级、以及运算规则
     */
    public abstract static class Operator {
        Operator() {
            initLevel();
        }

        /**
         * 获取操作符的优先级
         *
         * @param op 操作符
         * @return 优先级
         */
        public abstract Integer getLevel(String op);

        /**
         * 设置各个运算符的优先级，
         * 并放到HashMap level中，
         * K: 运算符
         * V: 优先级
         */
        public abstract void initLevel();

        /**
         * 判断第一个操作符是否优先于第二个操作符
         *
         * @param op1 第一操作符
         * @param op2 第二操作符
         * @return 是否优先
         */
        public abstract boolean isPriority(String op1, String op2);

        /**
         * 是否是操作符
         *
         * @param str 字符串
         * @return 是否
         */
        public abstract boolean isOp(String str);

        /**
         * 原子表达式计算
         *
         * @param x  第一个操作数
         * @param y  第二操作数
         * @param op 运算符
         * @return 运算结果
         */
        public abstract String atomicCalculate(String x, String y, String op);

    }

    /**
     * 定义默认的运算操作
     */
    private Operator operator = new Operator() {
        //定义
        final String PLUS = "+";
        final String MINUS = "-";
        final String MULTIPLY = "*";
        final String DIVIDE = "/";

        /**
         * 获取操作符的优先级
         *
         * @param op 操作符
         * @return 优先级
         */
        public Integer getLevel(String op) {
            return level.get(op);
        }

        /**
         * 设置优先级
         */
        public void initLevel() {
            level.put(PLUS, 0);
            level.put(MINUS, 0);
            level.put(MULTIPLY, 1);
            level.put(DIVIDE, 1);
        }

        /**
         * 判断第一个操作符是否优先于第二个操作符
         *
         * @param op1 第一操作符
         * @param op2 第二操作符
         * @return 是否优先
         */
        public boolean isPriority(String op1, String op2) {
            return level.get(op1) >= level.get(op2);
        }

        /**
         * 是否是操作符
         *
         * @param str 字符串
         * @return 是否
         */
        @Override
        public boolean isOp(String str) {
            return level.containsKey(str);
        }

        /**
         * 原子表达式计算
         *
         * @param x  第一个操作数
         * @param y  第二操作数
         * @param op 运算符
         * @return 运算结果
         */
        public String atomicCalculate(String x, String y, String op) {
            switch (op) {
                case PLUS:
                    return String.valueOf(Double.valueOf(x) + Double.valueOf(y));
                case MINUS:
                    return String.valueOf(Double.valueOf(x) - Double.valueOf(y));
                case MULTIPLY:
                    return String.valueOf(Double.valueOf(x) * Double.valueOf(y));
                case DIVIDE:
                    return String.valueOf(Double.valueOf(x) / Double.valueOf(y));
                default:
                    return "0";
            }
        }
    };

    /**
     * 左括号
     */
    private static final String LEFT_BRACKET = "(";
    /**
     * 右括号
     */
    private static final String RIGHT_BRACKET = ")";

    /**
     * 用户转换运算的栈
     */
    private final Stack<String> stack = new Stack<>();

    /**
     * 空格作为元数据分隔
     */
    private static final String SEPARATOR = " ";

    /**
     * 默认为数字运算
     */
    private static final String DEFAULT_PATTERN = "^(-?\\d+)(\\.\\d+)?$";

    public Eval() {
        this.dataPattern = Pattern.compile(DEFAULT_PATTERN);
    }

    public Eval(Operator operator) {
        this.dataPattern = Pattern.compile(DEFAULT_PATTERN);
        this.operator = operator;
    }

    public Eval(Pattern dataPattern, Operator operator) {
        this.dataPattern = dataPattern;
        this.operator = operator;
    }

    /**
     * 运算
     * @param expression 表达式
     * @return 运算结果
     */
    public String eval(String expression) {
        return calculatePostfixExpression(this.transToPostfixExpression(expression.split(SEPARATOR)));
    }

    /**
     * 中缀表达式 转 后缀表达式
     *
     * @param infixExpression 中缀表达式
     * @return 后缀表达式
     */
    private String[] transToPostfixExpression(String[] infixExpression) {
        StringBuilder sb = new StringBuilder(infixExpression.length);

        for (String s : infixExpression) {
            //1、左括号(，直接入栈
            if (LEFT_BRACKET.equals(s)) {
                stack.push(s);
                continue;
            }
            //2、右括号)，直接丢弃，并将陆续取出栈顶元素，直到左括号( 被取出为止
            if (RIGHT_BRACKET.equals(s)) {
                String top = stack.pop();
                while (!LEFT_BRACKET.equals(top)) {
                    sb.append(top).append(SEPARATOR);
                    top = stack.pop();
                }
                continue;
            }
            //3、是运算符
            if (operator.isOp(s)) {
                //3.1、栈是空的，直接入栈
                if (stack.empty()) {
                    stack.push(s);
                    continue;
                }
                //3.2、栈非空
                //3.2.0、取出栈顶元素
                String top = stack.pop();
                //3.2.1、遇到左括号，直接放回去
                if (LEFT_BRACKET.equals(top)) {
                    stack.push(top);
                    stack.push(s);
                    continue;
                }
                //3.2.1、栈顶的操作符  优先级 大于等于 当前操作符，则写入后缀表达式
                boolean flag = true;//是否需要回收
                while (operator.isPriority(top, s)) {
                    flag = false;
                    sb.append(top).append(SEPARATOR);
                    if (!stack.empty()) {
                        top = stack.pop();
                        flag = true;
                    } else {
                        break;
                    }
                }
                //3.2.2、栈顶操作符 优先级小于 当前操作符，则将二者按顺序放回栈中
                if (flag) stack.push(top);
                stack.push(s);
                continue;
            }
            //4、是数据，直接写入后缀表达式
            if (dataPattern.matcher(s).matches()) {
                sb.append(s).append(SEPARATOR);
                continue;
            }

            throw new RuntimeException("表达式不合法");
        }
        //全部出栈
        while (!stack.empty()) {
            sb.append(stack.pop()).append(SEPARATOR);
        }

        return sb.toString().trim().split(SEPARATOR);
    }

    /**
     * 计算后缀表达式
     * @param postfixExpression 后缀表达式
     * @return 值
     */
    private String calculatePostfixExpression(String[] postfixExpression) {
        for (String s : postfixExpression) {
            //1、是数据，直接入栈
            if (dataPattern.matcher(s).matches()) {
                stack.push(s);
                continue;
            }
            //2、是运算符，出栈两个元素运算，将结果入栈
            if (operator.isOp(s)) {
                //第二操作数
                String y = stack.pop();
                //第一操作数
                String x = stack.pop();
                //运算结果，入栈
                String c = operator.atomicCalculate(x, y, s);
                stack.push(c);
                continue;
            }
            throw new RuntimeException("表达式不合法");
        }
        //栈中剩下唯一的元素就是计算的结果
        String result = stack.pop();
        if (!stack.empty()) {
            throw new RuntimeException("表达式不合法");
        }
        return result;
    }

    public static void main(String[] args) {
        Eval evalExpression = new Eval();
        String exp = "11 + ( 31 - 100 ) * 73.4 + -18 / 2";
        System.out.println(11 + (31 - 100) * 73.4 + -18 / 2);
        System.out.println(evalExpression.eval(exp));

        //自定义 运算规则
        Eval eval = new Eval(new Operator(){
            /**
             * 获取操作符的优先级
             *
             * @param op 操作符
             * @return 优先级
             */
            @Override
            public Integer getLevel(String op) {
                return null;
            }

            /**
             * 设置优先级
             */
            @Override
            public void initLevel() {

            }

            /**
             * 判断第一个操作符是否优先于第二个操作符
             *
             * @param op1 第一操作符
             * @param op2 第二操作符
             * @return 是否优先
             */
            @Override
            public boolean isPriority(String op1, String op2) {
                return false;
            }

            /**
             * 是否是操作符
             *
             * @param str 字符串
             * @return 是否
             */
            @Override
            public boolean isOp(String str) {
                return false;
            }

            /**
             * 原子表达式计算
             *
             * @param x  第一个操作数
             * @param y  第二操作数
             * @param op 运算符
             * @return 运算结果
             */
            @Override
            public String atomicCalculate(String x, String y, String op) {
                return null;
            }
        });
    }

}
