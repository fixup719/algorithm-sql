import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] arr;
    static ArrayList<Integer> aLst = new ArrayList<>();
    static ArrayList<Integer> bLst = new ArrayList<>();

    static void separate(int cur, int sum, int end) {
        if (sum > C) return;

        if (cur == end) {
            if (end == N / 2) aLst.add(sum);
            else bLst.add(sum);
            return;
        }

        // 현재 가방 선택
        separate(cur + 1, sum + arr[cur], end);

        // 현재 가방 선택 X
        separate(cur + 1, sum, end);
    }

    static int binarySearch(int aNum) {
        int s = 0, e = bLst.size() - 1, mid, ans = 0;
        while (s <= e) {
            mid = (s + e) / 2;

            if (aNum + bLst.get(mid) > C) {
                e = mid - 1;
            } else {
                ans = mid + 1;
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
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // a리스트 만들기
        separate(0, 0, N / 2);

        // b리스트 만들기
        separate(N / 2, 0, N);

        Collections.sort(bLst);

        int num, cnt = 0;
        for (int i = 0, size = aLst.size(); i < size; i++) {
            num = aLst.get(i);
            cnt += binarySearch(num);
        }

        System.out.println(cnt);
    }
}