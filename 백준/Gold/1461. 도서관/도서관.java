import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pos = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pos);
        int max = Math.max(Math.abs(pos[0]), Math.abs(pos[N - 1])); // 가정 멀리있는 책 위치
        int answer = 0, start = 0, end = 0;
        // 음수 그룹
        while (end < N && pos[start] < 0) {
            // 책 두고 오기
            if (pos[start] < 0 && pos[end] > 0) {
                answer += Math.abs(pos[start]) * 2;
                break;
            } else if (end - start + 1 == M) {
                answer += Math.abs(pos[start]) * 2;
                start = end + 1;
            } else if (end == N - 1) {
                answer += Math.abs(pos[start]) * 2;
            }
            end++;
        }

        // 양수 그룹
        start = N - 1; end = N - 1;
        while ( 0 <= start && pos[end] > 0) {
            // 책 두고 오기
            if (pos[end] > 0 && pos[start] < 0) {
                answer += pos[end] * 2;
                break;
            } else if (end - start + 1 == M) {
                answer += pos[end] * 2;
                end = start - 1;
            } else if (start == 0) {
                answer += pos[end] * 2;
            }
            start--;
        }

        System.out.println(answer - max);

    }
}

