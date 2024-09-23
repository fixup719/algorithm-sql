import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int x, y, cnt = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() >= y) {
                if (stack.pop() > y) cnt++;
            }

            stack.push(y);
        }

        while (!stack.isEmpty()) {
            if (stack.pop() > 0) cnt++;
        }

        System.out.println(cnt);
    }
}

