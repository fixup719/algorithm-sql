import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    // target보다 크거나 같은 값들 중 가장 왼쪽 인덱스 찾기
    static int lowerBound(int target) {
        int s = 0;
        int e = list.size() - 1;
        int answer = list.size();
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;

            if (list.get(mid) >= target) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list.add(arr[0]);
        int idx; // 리스트에 있는 수 중 arr[i]보다 크거나 같은 값들 중 가장 왼쪽 인덱스
        for (int i = 1; i < N; i++) {
            idx = lowerBound(arr[i]);

            if (idx == list.size()) {
                list.add(arr[i]);
            } else {
                list.set(idx, arr[i]);
            }

        }

        System.out.println(list.size());
        br.close();
    }
}