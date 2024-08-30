import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int height;
        long sameCnt;

        Node(int height, long sameCnt) {
            this.height = height;
            this.sameCnt = sameCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 내림차순 형태로 유지(LIFO시 오름차순 형태로 출력)
        Stack<Node> stack = new Stack<>();
        // 동일한 거 담을 임시 stack
        Stack<Integer> tmp = new Stack<>();
        // 정답변수
        long ans = 0, cnt;
        for (int i = 0; i < N; i++) {
            cnt = 1;
            if (!stack.isEmpty()) {
                // 동일한 높이를 만나 거나 낮은 높이를 만난 경우
                while (!stack.isEmpty() && (stack.peek().height <= arr[i])) {
                    if (stack.peek().height == arr[i]) cnt += stack.peek().sameCnt;
                    ans += stack.peek().sameCnt;
                    stack.pop();
                }

                // 더 높은 높이를 만난 경우
                if (!stack.isEmpty() && stack.peek().height > arr[i]) {
                    ans++;
                }
            }

            stack.push(new Node(arr[i], cnt));
        }

        System.out.println(ans);
    }
}

