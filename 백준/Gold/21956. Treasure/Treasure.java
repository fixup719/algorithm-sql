import java.io.*;
import java.util.*;

public class Main {

    static class Char {
        char c;
        int cnt;

        Char(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        Stack<Char> stack = new Stack<>();

        char cur;
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            cur = str.charAt(i);

            if (!stack.isEmpty() && cur == stack.peek().c) {
                stack.peek().cnt++;

                if (stack.peek().cnt == K) {
                    stack.pop();
                }
            }
            else {
                stack.push(new Char(cur, 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            for (int i = 0; i < stack.peek().cnt; i++) {
                sb.append(stack.peek().c);
            }
            stack.pop();
        }
        System.out.println(sb.reverse());
    }
}