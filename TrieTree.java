package class01;

import java.util.HashMap;

/**
 * @author pacai
 * @version 1.0
 */
@SuppressWarnings("all")
public class TrieTree {
    private static class Node {
        private int pass;
        private int end;
        private Node[] next;

        public Node() {
            pass = 0;
            end = 0;
            next = new Node[26];
        }
    }

    public static class Trie1 {
        private Node root;

        public Trie1() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node cur = root;
            cur.pass++;
            int path;
            for (int i = 0; i < word.length(); i++) { //从左往右遍历
                path = word.charAt(i) - 'a';//找到路径
                if (cur.next[path] == null) {
                    cur.next[path] = new Node();
                }
                cur = cur.next[path];
                cur.pass++;
            }
            cur.end++;
        }

        //word加入几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node cur = root;
            int path;
            for (int i = 0; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (cur.next[path] == null) {
                    return 0;
                }
                cur = cur.next[path];
            }
            return cur.end;
        }

        //所有加入的字符串，有多少以pre作为前缀
        public int prefixSearch(String pre) {
            if (pre == null) {
                return 0;
            }
            Node cur = root;
            int path;
            for (int i = 0; i < pre.length(); i++) {
                path = pre.charAt(i) - 'a';
                if (cur.next[path] == null) {
                    return 0;
                }
                cur = cur.next[path];
            }
            return cur.pass;
        }

        //删除
        public void delete(String word) {
            if (word == null || search(word) == 0) { // 确保有加入该字符串才可以执行删除
                return;
            }
            Node cur = root;
            cur.pass--;
            int path;
            for (int i = 0; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (--cur.next[path].pass == 0) {
                    cur.next[path] = null;
                    return;
                }
                cur = cur.next[path];
            }
            cur.end--;
        }


        //使用HashMap来记录字符，则可以动态的控制输入字符的数量
        static class TrieTree2 {
            private class Node {
                private int pass;
                private int end;
                private HashMap<Integer, Node> next;
            }

            private Node root;

            public TrieTree2() {
                this.root = new Node();
            }

            //添加字符串
            public void insert(String word) {
                if (word == null) {
                    return;
                }
                Node cur = root;
                cur.pass++;
                for (int i = 0; i < word.length(); i++) {
                    int path = word.charAt(i) - '0';
                    if (!cur.next.containsKey(path)) {
                        cur.next.put(path, new Node());
                    }
                    cur = cur.next.get(path);
                    cur.pass++;
                }
                cur.end++;
            }

            //查找字符串
            public int search(String word) {
                if (word == null) {
                    return 0;
                }
                Node cur = root;
                for (int i = 0; i < word.length(); i++) {
                    int path = word.charAt(i) - '0';
                    if (!cur.next.containsKey(path)) {
                        return 0;
                    }
                    cur = cur.next.get(path);
                }
                return cur.end;
            }

            //查询以传入字符串为前缀的数量
            public int prefixSearch(String pre) {
                if (pre == null) {
                    return 0;
                }
                Node cur = root;
                for (int i = 0; i < pre.length(); i++) {
                    int path = pre.charAt(i) - '0';
                    if (!cur.next.containsKey(path)) {
                        return 0;
                    }
                    cur = cur.next.get(path);
                }
                return cur.pass;
            }

            //删除字符串
            public void delete(String word) {
                if (word == null || search(word) < 1) {
                    return;
                }
                Node cur = root;
                cur.pass--;
                for (int i = 0; i < word.length(); i++) {
                    int path = word.charAt(i) - '0';
                    cur = cur.next.get(path);
                    cur.pass--;
                    if(cur.pass == 0){
                        cur.next.remove(path);
                    }
                }
                cur.end--;
            }
        }

    }
}
