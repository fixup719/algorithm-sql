import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char cur;
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            cur = str.charAt(i);
            if (cur == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    cnt++;
                }
            } else {
                stack.push(cur);
            }
        }

        cnt += stack.size();

        System.out.println(cnt);
    }
}

