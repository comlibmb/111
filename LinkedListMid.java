package class01;

import java.util.ArrayList;

/**
 * @author pacai
 * @version 1.0
 */
public class LinkedListMid {
    public static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    Node<?> head;

    //泛型方法
    public<T> Node<T> midOrUpMidNOde(Node<T> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node<T> fast = head.next.next;
        Node<T> slow = head.next;
        //链表有三个结点以上
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    //验证方法
    public<T> Node<T> right1(Node<T> head){
        ArrayList<Node<T>> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        return !list.isEmpty() ? list.get((list.size() - 1) / 2) : head;
    }
}
