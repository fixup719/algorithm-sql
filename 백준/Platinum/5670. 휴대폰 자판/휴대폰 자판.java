import java.io.*;
import java.util.*;

public class Main {
    
    static class Node{
        Map<Character, Node> child = new TreeMap<>();
        boolean isEndOfWord = false;
    }
    
    static class Trie{
        Node root;

        Trie() {
            this.root = new Node();
        }

        public void insert(String str) {
            Node curNode = this.root;

            char c;
            for (int i = 0, len = str.length(); i < len; i++) {
                c = str.charAt(i);
                curNode.child.putIfAbsent(c, new Node());
                curNode = curNode.child.get(c);
            }

            curNode.isEndOfWord = true;
        }

        public double search(String str) {
            Node curNode = this.root;

            char c; int size; double cnt = 0;
            for (int i = 0, len = str.length(); i < len; i++) {
                c = str.charAt(i);

                size = curNode.child.size();

                if (i == 0|| size > 1) cnt++;
                else if (curNode.isEndOfWord && !curNode.child.isEmpty()) cnt++;

                curNode = curNode.child.get(c);
            }

            return cnt;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        try {
            int N;
            String str;
            String[] lst;
            double sum;
            while (true) {
                N = Integer.parseInt(br.readLine());

                Trie trie = new Trie();
                lst = new String[N];

                for (int i = 0; i < N; i++) {
                    str = br.readLine();
                    trie.insert(str);
                    lst[i] = str;
                }

                sum = 0;
                for (int i = 0; i < N; i++) {
                    sum += trie.search(lst[i]);
                }

                sb.append(String.format("%.2f", (sum / N))).append("\n");
            }
        } catch (Exception e) {
            System.out.println(sb);
        }
    }
}