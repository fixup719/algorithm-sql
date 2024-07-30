import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    static long M;
    static long[] arr;
    static ArrayList<Long> alst = new ArrayList<>();
    static ArrayList<Long> blst = new ArrayList<>();

    static void separate(int cur, int end, long sum) {
        if (sum > M) return;

        if (cur == end) {
            if (end == N / 2) alst.add(sum);
            else blst.add(sum);
            return;
        }

        separate(cur + 1, end, sum + arr[cur]);
        separate(cur + 1, end, sum);
    }

    static int binarySearch(long aNum) {
        int s = 0, e = blst.size() - 1, mid, ans = -1;
        long sum;
        while (s <= e) {
            mid = (s + e) / 2;
            sum = aNum + blst.get(mid);

            if (sum <= M) {
                ans = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        separate(0, N / 2, 0);
        separate(N / 2, N, 0);

        Collections.sort(blst);

        long num, cnt = 0;
        int idx;
        for (int i = 0, size = alst.size(); i < size; i++) {
            num = alst.get(i);
            idx = binarySearch(num);

            if (idx != -1) {
                cnt += (idx + 1);
            }
        }

        System.out.println(cnt);
    }
}