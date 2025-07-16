package class01;

import java.util.Stack;

/**
 * @author pacai
 * @version 1.0
 * 回文链表
 */
public class IsPalindrome {
    public static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    //need O(n) extra space
    public<T> boolean isPalindrome1(Node<T> head) {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> cur = head;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while(!stack.isEmpty()){
            if(stack.pop().data != head.data){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //need O(1) extra space
    public static<T> boolean isPalindrome2(Node<T> head) {
        if(head == null || head.next == null){
            return true;
        }
        Node<T> n1 = head;
        Node<T> n2 = head;
        while(n1.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next; //n2为中点的下一个结点
        n1.next = null;//将中点的next指针置为空
        Node<T> n3 = null;
        while(n2 != null){ //反转链表
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        n3 = n1;
        n2 = head;
        boolean res =  true;
        while(n1 != null && n2 != null){
            if(n1.data != n2.data){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        //最后还要把链表恢复
        n1 = n3.next;
        n3.next = null;
        while(n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
