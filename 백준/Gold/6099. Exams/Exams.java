import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long T;
    static long[] arr;
    static ArrayList<Long> alst = new ArrayList<>();
    static ArrayList<Long> blst = new ArrayList<>();

    static void separate(int cur, int end, long sum) {
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

            if (T <= sum) {
                ans = mid;
                e = mid - 1;
            } else {
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
        T = Long.parseLong(st.nextToken());

        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        separate(0, N / 2, 0);
        separate(N / 2, N, 0);
        Collections.sort(blst);

        int idx;
        long num, cnt = 0;
        for (int i = 0, size = alst.size(); i < size; i++) {
            num = alst.get(i);

            idx = binarySearch(num);
            if (idx != -1) {
                cnt += (blst.size() - idx);
            }
        }

        System.out.println(cnt);
    }
}