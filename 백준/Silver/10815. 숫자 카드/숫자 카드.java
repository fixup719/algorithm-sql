import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int binarySearch(int s, int e, int key, int[] arr) {
        int answer = 0;
        int mid;

        while (s <= e) {
            mid = (s + e) / 2;

            if (arr[mid] < key) {
                s = mid + 1;
            } else if (arr[mid] > key) {
                e = mid - 1;
            } else {
                answer = 1;
                break;
            }
        }
        return answer;
    }


    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(binarySearch(0, N - 1, nums[i], cards)+" ");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}