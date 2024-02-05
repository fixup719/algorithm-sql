import java.io.*;
import java.util.*;


public class Main {
    static int T, N;
    static int[] arr;
    static int dist;
    static int answer;

    static void binarySearch(int s, int e, int key) {
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;

            if (arr[mid] < key) {
                s = mid + 1;
            } else if (arr[mid] > key) {
                e = mid - 1;
            } else {
                answer++;
                return;
            }
        }

    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    dist = arr[j] - arr[i];
                    binarySearch(j, N - 1, arr[j] + dist);
                }
            }

            sb.append(answer+"\n");
        }





        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}