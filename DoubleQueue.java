package class01;

/**
 * @author pacai
 * @version 1.0
 */
public class DoubleQueue<T> {
    Node head;
    Node tail;

    class Node {
        T data;
        Node next;
        Node last;

        public Node(T data) {
            this.data = data;
        }
    }


    public void addFromHead(Node head, T data) {
        Node node = new Node(data);
        if (head == null){
            head = tail = node;
        }else{
            node.next = head;
            head.last = node;
            head = node;
        }
    }

    public void addFromTail(Node tail, T data) {
        Node node = new Node(data);
        if(tail == null){
            head = tail = node;
        }else{
            tail.next = node;
            node.last = tail;
            tail = node;
        }
    }

}
