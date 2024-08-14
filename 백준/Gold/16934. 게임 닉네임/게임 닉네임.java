import java.io.*;
import java.util.*;

public class Main {

    static class Node{
        Map<Character, Node> child = new TreeMap<>();
        int isEndOfWord = 0;
    }

    static class Trie{
        Node root;

        Trie() {
            this.root = new Node();
        }

        // 삽입 + 별칭 만들기
        public String insert(String str) {
            Node curNode = this.root;

            char c;
            String ans = "";
            boolean flag = false;
            for (int i = 0, len = str.length(); i < len; i++) {
                c = str.charAt(i);

                if (!curNode.child.containsKey(c)) {
                    // 자식 노드에 존재하지 않는다면
                    curNode.child.put(c, new Node());

                    // 첫글자인경우는 무조건 별칭에 추가
                    if (i == 0) ans += c;

                    // flag가 true인 경우 -> 이전 글자가 이미 앞서 존재한 접두사에 포함되는 경우
                    // 현재 가리키는 글자를 별칭에 추가하고, flag는 false로
                    if (flag) {
                        ans += c;
                        flag =  false;
                    }
                } else {
                    // 이미 존재한 다면
                    // flag를 true로하고, 별칭에 해당 문자 추가
                    flag = true;
                    ans += c;
                }

                curNode = curNode.child.get(c);
            }

            // 현재 가리키는 마지막 글자가 0보다 크다는 것은
            // 이전에 동일한 닉네임이 존재한다는 의미므로
            // 별칭에 중복된 닉네임 개수만큼 추가하기
            if (curNode.isEndOfWord > 0) {
                ans += String.valueOf(curNode.isEndOfWord + 1);
            }

            curNode.isEndOfWord++;
            return ans;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        String str, ret;
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            ret = trie.insert(str);
            sb.append(ret).append("\n");
        }

        System.out.println(sb);
    }
}