package cn.suvue.discipline.practice.array;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 基于java实现的出栈入栈操作
 *
 * @author suvue
 * @date 2019/12/30
 */
public class MyStack {

    /**
     * 存储栈内元素的数组
     */
    private int[] storage;

    /**
     * 栈的总容量
     */
    private int capacity;

    /**
     * 栈的当前容量
     */
    private int count;

    /**
     * 扩容因子
     */
    private static final int GROW_FACTOR = 2;

    /**
     * 无参构造方法
     */
    public MyStack() {
        this.capacity = 8;
        this.storage = new int[8];
        this.count = 0;
    }

    /**
     * 有参构造方法
     */
    public MyStack(int initCapacity) {
        if (initCapacity < 1) {
            throw new IllegalArgumentException("initCapacity is too small");
        }
        this.capacity = initCapacity;
        this.storage = new int[initCapacity];
        this.count = 0;
    }

    /**
     * 入栈
     */
    public void push(int value) {
        if (count == capacity) {
            ensureCapacity();
        }
        storage[count++] = value;
    }

    /**
     * 弹出并返回栈顶元素
     */
    public int pop() {
        count--;
        if (count == -1) {
            throw new IllegalArgumentException("empty stack!");
        }
        return storage[count];
    }

    /**
     * 只返回栈顶元素
     */
    public int peek() {
        if (count == 0) {
            throw new IllegalArgumentException("empty stack");
        }
        return storage[count - 1];
    }

    /**
     * 判空
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 获取当前栈容量
     */
    public int size() {
        return count;
    }

    /**
     * 扩容机制
     */
    private void ensureCapacity() {
        int newCapacity = capacity * GROW_FACTOR;
        storage = Arrays.copyOf(storage, newCapacity);
        capacity = newCapacity;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(5);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.out.println(myStack.size());//输出5
        System.out.println(myStack.peek());//输出5
        System.out.println(myStack.peek());//输出5

        for (int i = 0; i < 5; i++) {
            myStack.pop();
        }
        System.out.println(myStack.isEmpty());//true
        System.out.println(myStack.size());//输出0
    }
}
