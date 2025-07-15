package class01;

import java.util.ArrayList;

/**
 * @author pacai
 * @version 1.0
 * 快慢指针
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
    public<T> Node<T> midOrUpMidNode(Node<T> head) {
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
    //奇数中点，偶数中点上
    public<T> Node<T> right1(Node<T> head){
        ArrayList<Node<T>> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        return !list.isEmpty() ? list.get((list.size() - 1) / 2) : head;
    }

    //奇数中点，偶数中点下
    public<T> Node<T> midOrDownMidNode(Node<T> head){
        if(head == null || head.next == null){
            return head;
        }
        Node<T> fast = head.next;
        Node<T> slow = head.next;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //验证方法
    public<T> Node<T> right2(Node<T> head){
        ArrayList<Node<T>> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        return !list.isEmpty() ? list.get(list.size() / 2) : head;
    }

    //奇数返回中点前一个，偶数返回上中点前一个
    public<T> Node<T> upMidOrDouUpMidNode(Node<T> head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node<T> fast = head.next.next;
        Node<T> slow = head;
        if(fast.next != null && fast.next.next != null){ //5,7,9...才会往前走一步
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //验证方法
    public<T> Node<T> right3(Node<T> head){
        ArrayList<Node<T>> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        return (!list.isEmpty() && (list.size() >= 3)) ? list.get((list.size() - 1) / 2 - 1) : head;
    }
    //奇数返回中点前一个，偶数返回下中点前一个
    public<T> Node<T> UpMidOrUpMidNode(Node<T> head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node<T> fast = head.next;
        Node<T> slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    //验证方法
    public<T> Node<T> right4(Node<T> head){
        ArrayList<Node<T>> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        return (!list.isEmpty() && list.size() > 3) ? list.get(list.size() / 2 - 1) : head;
    }
}
