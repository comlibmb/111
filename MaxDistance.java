package class01;

/**
 * @author pacai
 * @version 1.0
 * 求二叉树两个结点的最大距离
 */
public class MaxDistance {
    public static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
        }
    }


    private static class Info {
        int height;
        int maxDistance;

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public static <T> int maxDistance(Node<T> head) {
        return process(head).maxDistance;
    }


    public static <T> Info process(Node<T> X) {
        if (X == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        //动态规划
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1);
        return new Info(height, maxDistance);
    }
}
