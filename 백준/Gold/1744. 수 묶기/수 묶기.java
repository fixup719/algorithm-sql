import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        /*
            음수 * 양수 or 양수 * 0 -> 수가 더 작아진다
            양수 * 양수 or 음수 * 음수 or 음수 * 0 -> 최대한 수가 커진다
            양수이지만 1인 경우에는 곱하지 X
        */
        int sum = 0;

        // 양수끼리 우선 더하기
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= 0) break;
            if (0 <= i - 1 && arr[i - 1] > 1) {
                sum += arr[i] * arr[i - 1];
                i--;
            } else {
                sum += arr[i];
            }
        }

        // 음수끼리 더하기 or 음수 & 0
        for (int i = 0; i < N; i++) {
            if (arr[i] > 0) continue;
            if (i + 1 < N && arr[i + 1] <= 0) {
                sum += arr[i] * arr[i + 1];
                i++;
            } else {
                sum += arr[i];
            }
        }

        System.out.println(sum);
    }
}