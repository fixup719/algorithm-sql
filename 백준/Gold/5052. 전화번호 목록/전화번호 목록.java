import java.io.*;
import java.util.*;

public class Main {

    static class Node{
        Map<Character, Node> child = new TreeMap<>();
        boolean isEndOfWOrd;
    }

    static class Trie{
        Node root;

        Trie() {
            this.root = new Node();
        }

        // 트라이 생성
        public void insert(String str) {
            Node curNode = this.root;

            char c;
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);

                curNode.child.putIfAbsent(c, new Node());

                curNode = curNode.child.get(c);
            }

            curNode.isEndOfWOrd = true;
        }

        // 특정 문자열 탐색
        public boolean search(String str) {
            Node curNode = this.root;

            char c;
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);

                if (curNode.child.containsKey(c)) {
                    curNode = curNode.child.get(c);
                } else {
                    return false;
                }
            }

            return curNode.child.isEmpty() && curNode.isEndOfWOrd;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int N; String str; String[] list;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            Trie trie = new Trie();
            list = new String[N];

            for (int i = 0; i < N; i++) {
                str = br.readLine();


                trie.insert(str);
                list[i] = str;
            }

            boolean ret = true;
            for (int i = 0; i < N; i++) {
                ret = trie.search(list[i]);

                if (!ret) break;
            }

            if (!ret) {
                sb.append("NO\n");
            } else {
                sb.append("YES\n");
            }
        }

        System.out.println(sb);
    }
}