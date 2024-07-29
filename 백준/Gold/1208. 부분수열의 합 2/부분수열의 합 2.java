import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[] arr;
    static ArrayList<Integer> aLst = new ArrayList<>();
    static ArrayList<Integer> bLst = new ArrayList<>();

    static void separate(int cur, int end, int sum) {
        if (cur == end) {
            if (end == N / 2) aLst.add(sum);
            else bLst.add(sum);
            return;
        }

        // 현재 숫자 선택 O
        separate(cur + 1, end, sum + arr[cur]);

        // 현재 숫자 선택 X
        separate(cur + 1, end, sum);
    }

    // 합이 S가 되는 가장 작은 인덱스 출력
    static int getMinIdx(int aNum) {
        int s = 0, e = bLst.size() - 1, mid, ans = -1;
        long sum;
        while (s <= e) {
            mid = (s + e) / 2;
            sum = aNum + bLst.get(mid);

            if (sum == S) {
                // 정답 변수에 담기
                ans = mid;
                // 최대한 작은 인덱스를 찾는 것이므로
                e = mid - 1;
            } else if (sum > S) {
                // 합을 줄여야 하므로
                e = mid - 1;
            } else {
                // 합을 늘려야 하므로
                s = mid + 1;
            }
        }

        return ans;
    }

    // 합이 S가 되는 가장 큰 인덱스 출력
    static int getMaxIdx(int aNum) {
        int s = 0, e = bLst.size() - 1, mid, ans = -1;
        long sum;
        while (s <= e) {
            mid = (s + e) / 2;
            sum = aNum + bLst.get(mid);

            if (sum == S) {
                // 정답 변수에 담기
                ans = mid;
                // 최대한 큰 인덱스를 찾는 것이므로
                s = mid + 1;
            } else if (sum > S) {
                // 합을 줄여야 하므로
                e = mid - 1;
            } else {
                // 합을 늘려야 하므로
                s = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        separate(0, N / 2, 0);
        separate(N / 2, N, 0);

        Collections.sort(bLst);
        int num, max, min;
        long cnt = 0;
        for (int i = 0, size = aLst.size(); i < size; i++) {
            num = aLst.get(i);
            max = getMaxIdx(num);
            min = getMinIdx(num);

            if (max != -1 && min != -1) {
                cnt += (max - min + 1);
            }
        }

        // S가 0인경우 부분 수열 크기가 0인 경우는 제거해야하므로 -1
        if (S == 0) System.out.println(cnt - 1);
        else System.out.println(cnt);
    }
}