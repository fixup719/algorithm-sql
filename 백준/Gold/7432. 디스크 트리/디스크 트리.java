import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static class Node {
        Map<String, Node> child = new TreeMap<>();
        int line = 0;
    }

    static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        public void insert(String[] name) {
            Node cur = this.root;

            String str;
            for (int i = 0, len = name.length; i < len; i++) {
                str = name[i];
                cur.child.putIfAbsent(str, new Node());
                cur = cur.child.get(str);
                cur.line = i + 1;
            }
        }

        public void search(Node cur) {
            for (String key : cur.child.keySet()) {
                for (int i = 0; i < cur.line; i++) sb.append(" ");
                sb.append(key);
                sb.append("\n");
                search(cur.child.get(key));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();

        int N = Integer.parseInt(br.readLine());

        String[] str;
        for (int i = 0; i < N; i++) {
            // \ 기준으로 구붙하기
            str = br.readLine().split("\\\\");
            trie.insert(str);
        }

        trie.search(trie.root);

        System.out.println(sb);
    }
}