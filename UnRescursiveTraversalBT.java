package class01;

import java.util.Stack;

/**
 * @author pacai
 * @version 1.0
 * 使用非递归实现前，中，后序遍历
 */
public class UnRescursiveTraversalBT {
    public static class TreeNode<T> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    '}';
        }
    }

    public static <T> void pre(TreeNode<T> head) {
        if (head != null) {
            Stack<TreeNode<T>> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
                System.out.println(head + " ");
            }
        }
    }


    public static <T> void in(TreeNode<T> head) {
        if (head != null) {
            Stack<TreeNode<T>> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head + " ");
                    head = head.right;
                }
            }
        }
    }


    public static <T> void post(TreeNode<T> head) {
        if (head != null) {
            Stack<TreeNode<T>> stack = new Stack<>();
            Stack<TreeNode<T>> be = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                be.push(head);
                if (head.left != null) {
                    stack.push(head.left);
                }
                if (head.right != null) {
                    stack.push(head.right);
                }
            }
            while (!be.isEmpty()) {
                System.out.println(be.pop() + " ");
            }
        }
    }
}
