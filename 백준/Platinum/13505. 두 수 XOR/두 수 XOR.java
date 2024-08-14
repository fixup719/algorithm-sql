import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        Map<Character, Node> child = new TreeMap<>();
        boolean isEndOfWord = false;
    }

    static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        // 삽입
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

        // XOR한 결과값 반환
        public int xor(String str) {
            Node curNode = this.root;

            int ans = 0; char c, tc;
            for (int i = 0, len = str.length(), digit = 29; i < len; i++, digit--) {
                c = str.charAt(i);

                // 나와 다른 수를 찾아야하므로 0 -> 1, 1->0으로 바꿔주고 해당 글자 찾기
                if (c == '0') tc = '1';
                else tc = '0';

                if (curNode.child.containsKey(tc)) {
                    // 나와 다른 숫자가 존재한다면
                    ans += Math.pow(2, digit);
                    curNode = curNode.child.get(tc);
                } else {
                    curNode = curNode.child.get(c);
                }
            }

            return ans;
        }
    }

    // 숫자가 최대 10억까지 가능하므로, 10억을 2진수로 바꾸면 총 30자리까지 갖게 된다
    // 따라서 이진수로 변환후 30 자릿수로 맞춰주기(빈공간은 0으로 채운다)
    static String toBinary(int num) {
        String toBinary = Integer.toBinaryString(num);

        StringBuilder sb = new StringBuilder();
        sb.append(toBinary);
        int size = toBinary.length();

        while (size < 30) {
            sb.insert(0, 0);
            size++;
        }

        return String.valueOf(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        String[] arr = new String[N];

        st = new StringTokenizer(br.readLine());
        int num; String binaryStr;
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(st.nextToken());
            binaryStr = toBinary(num);
            arr[i] = binaryStr;
            trie.insert(binaryStr);
        }

        String cur;
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, trie.xor(arr[i]));
        }

        System.out.println(max);
    }
}