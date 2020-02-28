package org.chiefdata.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author : Kevin
 * @Title : StackTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/26 19:19
 * @Modifyed By :
 */
public class StackTest {

    static Map<Character, Character> map = new HashMap<Character, Character>();

    static{

        map.put('[', ']');
        map.put('{', '}');
        map.put('(', ')');
    }

    public static void main(String[] args) {
//        Stack<Integer> stack = new ArrayStack<Integer>();
//
//        for (int i = 0; i < 10; i++) {
//            stack.push(i);
//        }
//
//        System.out.println(stack.toString());
//        stack.pop();
//        System.out.println(stack.toString());

//        String str1 = "[]{}()";
//        String str2 = "[]{)}";
//        String str3 = "]";
//        System.out.println(app1(str1));
//        System.out.println(app1(str2));
//        System.out.println(app1(str3));


        System.out.println("LinkedListStack:" + test2Stack(new LinkedListStack<Integer>()));
        System.out.println("ArrayStack:" + test2Stack(new ArrayStack<Integer>()));
    }

    /**
     * 括号 匹配。 []{}() 就算合法 []{)} 不合法 {[()]} 合法
     */
    public static boolean app1(String str){

//        Stack<Character> stack = new ArrayStack<Character>();

        Stack<Character> stack = new LinkedListStack<>();

        for (int i = 0; i < str.length(); i++) {
            char chi = str.charAt(i);
            if (chi == '[' || chi == '{' || chi == '('){
                stack.push(chi);
            }

            if (chi == ']' || chi == '}' || chi == ')'){
                if (stack.isEmpty()){
                    return false;
                }
                char top = stack.pop().charValue();
                if (chi != map.get(top).charValue()){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean app11(String s){
        String s_start = s.substring(0);
        int lastIndexOf = s.lastIndexOf(map.get(s_start));
        if (-1 == lastIndexOf){
            return false;
        }
        String str = s.substring(0, lastIndexOf + 1);
//        if ()
        return false;
    }

    public static long test2Stack(Stack<Integer> stack){
        int count = 10000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            stack.push(i);
        }
        for (int i = 0; i < count; i++) {
            stack.pop();
        }

        return System.currentTimeMillis() - start;
    }
}
