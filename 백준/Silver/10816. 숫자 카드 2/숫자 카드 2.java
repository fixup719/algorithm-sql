import java.io.*;
import java.util.*;


public class Main {

    static int getLeftIdx(int s, int e, int key, int[] arr) {
        int ans = -1;
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;

            if (arr[mid] == key) {
                ans = mid;
                e = mid - 1;
            } else if (arr[mid] < key) {
                s = mid + 1;
            } else {
                e = mid -1;
            }
        }
        return ans;
    }

    static int getRightIdx(int s, int e, int key, int[] arr) {
        int ans = -1;
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;

            if (arr[mid] == key) {
                ans = mid;
                s = mid + 1;
            } else if (arr[mid] < key) {
                s = mid + 1;
            } else {
                e = mid -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] nums = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);
        int r, l;
        for (int i = 0; i < M; i++) {
            r = getRightIdx(0, N - 1, nums[i], cards);
            l = getLeftIdx(0, N - 1, nums[i], cards);

            if( r < 0 || l < 0) sb.append(0 + " ");
            else sb.append(r - l + 1 + " ");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}