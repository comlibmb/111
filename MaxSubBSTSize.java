package class01;

/**
 * @author pacai
 * @version 1.0
 * 求二叉树中的最大搜索二叉树
 */
public class MaxSubBSTSize {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static class Info {
        public boolean isAllBST;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info(boolean isAllBST, int maxSubBSTSize, int min, int max) {
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    public static Info process(Node X) {
        if (X == null) return null;

        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);

        int min = X.data;
        int max = X.data;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }

        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        int maxSubBSTSize = 0;
        maxSubBSTSize = leftInfo == null ? 0 : leftInfo.maxSubBSTSize;
        maxSubBSTSize = rightInfo == null ? maxSubBSTSize : Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);

        boolean isAllBST = false;
        if (
                (leftInfo == null ? true : leftInfo.isAllBST)
                &&
                (rightInfo == null ? true : rightInfo.isAllBST)
                &&
                (leftInfo == null ? true : leftInfo.max < X.data)
                &&
                (rightInfo == null ? true : rightInfo.min > X.data)
        ) {
            maxSubBSTSize =
                    (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    +
                    (rightInfo == null ? 0 : rightInfo.min)
                    +
                    1;
            isAllBST = true;
        }


        return new Info(isAllBST, maxSubBSTSize, min, max);
    }
}
