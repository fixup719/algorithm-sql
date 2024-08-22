import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 스택 내부값이 오름차순 형태가 될것(<=)
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek() > arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty() || stack.peek() < arr[i]) answer++;

            stack.push(arr[i]);
        }

        System.out.println(answer);
    }
}