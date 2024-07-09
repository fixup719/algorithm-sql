import java.io.*;
import java.util.*;

public class Main {
    static int R, C, H, W;
    static int[][] arr;
    static int[][] prefix;

    static int binarySearch(int start, int end) {
        int s = start, e = end, mid, answer = -1;
        while (s <= e) {
            mid = (s + e) / 2; // quality rank의 중간값 가정

            // mid보다 쟉은 값은 1, 큰 값은 -1, 같은 값은 0
            // 퀄리티랭크가 작을수록 좋음
            // 3000*3000
            for (int i = 1; i < R + 1; i++) {
                for (int j = 1; j < C + 1; j++) {
                    if (arr[i][j] > mid) prefix[i][j] = -1; // mid보다 퀄리티랭크 높은애들(질낮)
                    else if (arr[i][j] < mid) prefix[i][j] = 1; // mid보다 퀄리티랭크 낮은애들(질높)
                    else prefix[i][j] = 0;
                }
            }

            // 2차원 누적합 배열 만들기  3000*3000
            for (int i = 1; i < R + 1; i++) {
                for (int j = 1; j < C + 1; j++) {
                    prefix[i][j] = prefix[i][j] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
                }
            }

            // H*W크기의 영역의 누적합이 0인것 찾기 -> 중간값을 의미함
            int sum; boolean check = false;
            for (int i = H; i < R + 1; i++) {
                for (int j = W; j < C + 1; j++) {
                    sum = prefix[i][j] - prefix[i - H][j] - prefix[i][j - W] + prefix[i - H][j - W];
                    if (sum >= 0) {
                        check = true;
                    }
                }
            }

            if (check) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[R + 1][C + 1];
        prefix = new int[R + 1][C + 1];
        int min = 1 << 30, max = 0 ;
        for (int i = 1; i < R + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < C + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        int ans = binarySearch(min, max);

        System.out.println(ans);
    }
}
