import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr;

    static int binarySearch(int start, int end) {
        int s = start, e = end, mid, sum, cnt = 0;
        int answer = -1;
        while (s <= e) {
            mid = (s + e) / 2; // 문제 합의 최솟값 (즉 받을 수 있는 최대 점수)

            sum = 0;
            cnt = 0;
            for (int i = 0; i < N; i++) {
                sum += arr[i];

                // 문제 합의 최솟값을 mid로 가정했으니 그룹의 합계는 mid보다 무조건 크거나 같아야함
                if (sum >= mid) {
                    // 크거나 같은 순간이 오면
                    cnt++; // 그룹수 카운트 + 1
                    sum = 0; // 그룹 합계는 0으로 초기화
                }
            }

            if (cnt < K) {
                // 그룹 개수가 K보다 작으면
                // 최소합을 줄여야 함
                e = mid - 1;
            } else if (cnt > K) {
                // 그룹 개수가 K보다 크면
                // 최소합을 늘려야함
                s = mid + 1;
            } else {
                // 그룹 개수와 K가 같다면 일단 정답 변수에 넣어주고
                answer = mid;
                // 최대한 점수를 크게 가져야 하므로 s를 늘린다.
                s = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        int ans = binarySearch(0, sum);

        System.out.println(ans);

    }
}
