import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static class Node {
        Map<String, Node> child = new TreeMap<>();
        int floor = 0;
    }

    static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        public void insert(ArrayList<String> lst) {
            Node cur = this.root;

            String str;
            for (int i = 0, len = lst.size(); i < len; i++) {
                str = lst.get(i);
                cur.child.putIfAbsent(str, new Node());
                cur = cur.child.get(str);
                cur.floor = i;
            }
        }

        public void search(Node cur) {
            String key; 
            for (Map.Entry<String, Node> entry : cur.child.entrySet()) {
                key = entry.getKey();
                for (int i = 0; i < cur.child.get(key).floor; i++) {
                    sb.append("--");
                }
                sb.append(key);
                sb.append("\n");
                search(cur.child.get(key));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Trie trie = new Trie();

        int N = Integer.parseInt(br.readLine());

        int K;
        ArrayList<String>[] lst = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            lst[i] = new ArrayList<>();
            for (int j = 0; j < K; j++) {
                lst[i].add(st.nextToken());
            }
            trie.insert(lst[i]);
        }

        trie.search(trie.root);

        System.out.println(sb);

    }
}