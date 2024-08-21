import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        long answer = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (!stack.isEmpty()) {
                if (stack.peek() >= arr[i]) {
                    answer += stack.peek() - arr[i];
                } else {
                    if (max < arr[i]) answer += arr[i] - max;
                    stack.pop();
                }
            }
            stack.push(arr[i]);
            max = Math.max(max, arr[i]);
        }
        System.out.println(answer);
    }
}