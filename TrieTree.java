package class01;

/**
 * @author pacai
 * @version 1.0
 */
public class TrieTree {
    private static class Node{
        private int pass;
        private int end;
        private Node[] next;

        public Node() {
            pass = 0;
            end = 0;
            next = new Node[26];
        }
    }

    public static class Trie1{
        private Node root;

        public Trie1() {
            root = new Node();
        }
        @SuppressWarnings("all")
        public void insert(String word){
            if(word == null){
                return;
            }
            Node cur = root;
            cur.pass++;
            int path;
            for (int i = 0; i < word.length(); i++) { //从左往右遍历
                path = word.charAt(i) - 'a';//找到路径
                if(cur.next[path] == null){
                    cur.next[path] = new Node();
                }
                cur = cur.next[path];
                cur.pass++;
            }
            cur.end++;
        }
    }
}
