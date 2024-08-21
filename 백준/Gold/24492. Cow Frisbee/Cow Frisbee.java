import java.io.*;
import java.util.*;

public class Main {

    static class Cow {
        int h;
        int idx;

        Cow(int h, int idx) {
            this.h = h;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long answer= 0;

        // 좌측에서 우측으로 탐색
        // 이전 건물보다 크거나 같은 경우만 체크
        Stack<Cow> leftToRight = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!leftToRight.isEmpty() && arr[i] >= leftToRight.peek().h) {
                answer += Math.abs(i - leftToRight.pop().idx) + 1;
            }
            leftToRight.push(new Cow(arr[i], i));
        }


        // 우측에서 좌측으로 탐색
        // 이전 건물보다 큰 경우만 체크
        Stack<Cow> rightToLeft = new Stack<>();
        for (int i = N; i >= 1; i--) {
            while (!rightToLeft.isEmpty() && arr[i] > rightToLeft.peek().h) {
                answer += Math.abs(i - rightToLeft.pop().idx) + 1;
            }
            rightToLeft.push(new Cow(arr[i], i));
        }

        System.out.println(answer);
    }
}