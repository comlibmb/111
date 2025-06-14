package class01;

/**
 * @author pacai
 * @version 1.0
 * 链表逆转
 */
@SuppressWarnings("all")
public class ReverseList {
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }


    static class DoubleNode<T> {
        T data;
        DoubleNode<T> next;
        DoubleNode<T> pre;

        public DoubleNode(T data) {
            this.data = data;
        }
    }


    public static Node reverseList(Node head) {
        Node<Integer> prev = null;
        Node<Integer> next = null;
        while (head != null) {
            next = next.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static DoubleNode reverseListDouble(DoubleNode head) {
        DoubleNode<Integer> prev = null;
        DoubleNode<Integer> next = null;
        while (head != null) {
            next = next.next;
            head.next = prev;
            head.pre = next;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static<T> Node<T> deleteFac(Node<T> head, T num) {
        while (head != null) {
            if (!head.data.equals(num)) {
                break;
            }
            head = head.next;
        }

        Node<T> pre = head;
        Node<T> cur = head;

        while(cur != null){
            if(cur.data.equals(num)){
                pre.next =cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


}
