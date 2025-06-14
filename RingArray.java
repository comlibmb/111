package class01;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Stack;

/**
 * @author pacai
 * @version 1.0
 */
//@SuppressWarnings("all")
public class RingArray {
    public static class MyQueue<T> {
        private int size = 0;
        private Object[] arr;
        private int limit;
        private int pushi;
        private int popi;

        public MyQueue(int limit) {
            this.limit = limit;
            pushi = popi = 0;
            arr = new Object[limit];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(T data) {
            if (size() == limit) {
                throw new RuntimeException("栈满");
            }
            size++;
            arr[pushi] = data;
            pushi = nextIndex(pushi);
        }

        public T pop() {
            if (size() == 0) {
                throw new RuntimeException("栈空");
            }
            size--;
            T ans = (T) arr[popi];
            popi = nextIndex(popi);
            return ans;
        }

        public int nextIndex(int index) {
            return index + 1 > limit ? 0 : index + 1;//等效于取模
        }

        public T peek() {
            if (size() == 0) {
                throw new RuntimeException("栈空");
            }
            return (T) arr[popi];
        }


    }


    public static class MyStack<T> {
        int size = 0;
        Object[] arr;
        int limit;

        public MyStack(int limit) {
            this.limit = limit;
        }

        public boolean push(T data) {
            if (size() == 0) {
                arr = new Object[limit];
            }
            if (isFull()) {
                return false;
            }
            arr[size++] = data;
            return true;
        }

        public T pop() {
            if (isEmpty()) {
                return null;
            }
            return (T) arr[--size];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public boolean isFull() {
            return size == limit;
        }
    }

    //最小栈
    static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        public void push(int data) {
            if (stackMin.isEmpty()) {
                stackMin.push(data);
            } else if (data < getMin()) {
                stackMin.push(data);
            } else {
                stackMin.push(stackMin.peek());
            }
            this.stackData.push(data);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("the stack is empty");
            }
            stackMin.pop();
            return stackData.pop();
        }

        public int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("the stack is empty");
            }
            return stackMin.peek();
        }

        //最小栈
        public static class MyStack2{
            private Stack<Integer> stackData;
            private Stack<Integer> stackMin;

            public MyStack2() {
                stackData = new Stack<>();
                stackMin = new Stack<>();
            }

            public void push(int data){
                if(stackMin.isEmpty()){
                    stackMin.push(data);
                }
                stackData.push(data);
                if(stackMin.peek() >= data){
                    stackMin.push(data);
                }
            }

            public int pop(){
                if(stackData.isEmpty()){
                    throw new RuntimeException("the stack is empty");
                }
                if(stackData.pop().equals(stackMin.peek())){
                    stackMin.pop();
                }
                return stackData.pop();
            }

            public int getMin(){
                if(stackMin.isEmpty()){
                    throw new RuntimeException("the stack is empty");
                }
                return stackMin.peek();
            }
        }

        //队列模拟栈
        public static class TwoQueueStack{
            private ArrayList<Integer> queuePush;
            private ArrayList<Integer> queuePop;

            public TwoQueueStack(ArrayList<Integer> queuePush, ArrayList<Integer> queuePop) {
                this.queuePush = queuePush;
                this.queuePop = queuePop;
            }

            public void push(int data){
                if(queuePush.isEmpty() && queuePop.isEmpty()){
                    queuePush.add(data);
                }else if(!queuePush.isEmpty()){
                    queuePush.add(data);
                }else{
                    queuePop.add(data);
                }
            }

            public void getStack(){
                if(queuePop.isEmpty() && queuePush.isEmpty()){
                    throw new RuntimeException("the stack is empty");
                }
                if(queuePop.isEmpty()) {
                    for (int i = 0; i < queuePush.size() - 1; i++) {
                        queuePop.add(queuePush.remove(0));
                    }
                }else if(queuePush.isEmpty()){
                    for (int i = 0; i < queuePop.size() - 1; i++) {
                        queuePush.add(queuePop.remove(0));
                    }
                }
            }

            public int pop(){
                getStack();
                return queuePush.size() == 1 ? queuePush.remove(0) : queuePop.remove(0);
            }

            public int peek(){
                getStack();
                return queuePush.size() == 1 ? queuePush.get(0) : queuePop.get(0);
            }

        }


        //栈模拟队列
        public static class TwoStackQueue {
            private Stack<Integer> stackPush;
            private Stack<Integer> stackPop;

            public TwoStackQueue() {
                stackPush = new Stack<>();
                stackPop = new Stack<>();
            }

            public void push(int data) {
                stackPush.push(data);
                pushTopPop();
            }

            public void pushTopPop() {
                if (stackPop.isEmpty()) {
                    while (!stackPush.isEmpty()) {
                        stackPop.push(stackPush.pop());
                    }
                }
            }

            public int pop() {
                if (stackPop.isEmpty() && stackPush.isEmpty()) {
                    throw new RuntimeException("the stack is empty");
                }
                pushTopPop();
                return stackPop.pop();
            }

            public int geek() {
                if (stackPop.isEmpty() && stackPush.isEmpty()) {
                    throw new RuntimeException("the stack is empty");
                }
                pushTopPop();
                return stackPop.peek();
            }



        }
    }


    public static void main(String[] args) {
        int limit = 20;
        int maxValue = 10000;
        int times = 500000;
        Random random = new Random();
        Stack<Integer> stack = new Stack<>();
        MyStack<Integer> myStack = new MyStack<>(limit);
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < random.nextInt() * limit; j++) {
                stack.push(random.nextInt() * maxValue);
                myStack.push(stack.pop());
            }
            for (int j = 0; j < stack.size(); j++) {
                if (!myStack.pop().equals(stack.pop())) {
                    System.out.println("Oops");
                    return;
                }
            }
        }
        System.out.println("test success");
    }

}
