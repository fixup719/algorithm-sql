import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.*;

// 20분
public class Main {
    /*
        문제
        1분마다 한 집 또는 두 집 앞의 눈을 각각 1만큼 치울 수 있음
        모든 집 앞 눈을 전부 치우는데 걸리는 최소 시간은?

        입력
        집의 수를 의미하는 정수 N(1 <= N <= 100)
        각각 집 앞에 쌓여 있는 눈의 양 ai (1 <= ai <= 2000)

        출력
        모든 집 앞 눈을 치우는데 최소 몇 분?(24시간(1440분) 넘는다면 -1출력)

        케이스
        1 2 3 -> 나머지 합 == 최대값 -> 최대값이 답

        1 3 4 5 -> 나머지합(8) > 최대값(5) -> 5
        1 3 3 4
        1 3 2 3
        1 3 1 2
        1 3 0 1
        1 2 0 0
        0 1 0 0
        0 0 0 0

        1 2 4 4 5 -> 11 > 5
        1 2 4 3 4
        1 2 4 2 3
        1 2 4 1 2
        1 2 4 0 1
        1 2 3 0 0
        1 1 2 0 0
        1 0 1 0 0
        0 0 0 0 0

        2 3 3 4 4 8  16 > 8 -> 8 + (16-8)/2 + (16-8)%2
        2 3 3 4 3 7
        2 3 3 4 2 6
        2 3 3 4 1 5
        2 3 3 4 0 4
        2 3 3 3 0 3
        2 3 3 2 0 2
        2 3 3 1 0 1
        2 3 3 0 0 0
        2 2 2 0 0 0
        2 1 1 0 0 0
        1 0 1 0 0 0
        0 0 0 0 0 0

        1 1 4 5 -> 6 > 5
        1 1 3 4
        1 1 2 3
        1 1 1 2
        1 1 0 1
        1 0 0 0
        0 0 0 0

        1 2 4 -> 나머지합(3) < 최대값(4) -> 나머지합 + (최대값-나머지합)
    */
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        Arrays.sort(arr);

        int remain = sum - arr[N - 1];
        int max = arr[N - 1];

        int ans;
        if (remain == max) {
            ans = max;
        } else if (remain > max) {
            ans = max + (remain - max) / 2 + (remain - max) % 2;
        } else {
            ans = remain + (max - remain);
        }

        if (ans > 1440) System.out.println("-1");
        else System.out.println(ans);

        br.close();
    }
}