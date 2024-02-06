import java.io.*;
import java.util.*;


public class Main {
    static int answer = Integer.MAX_VALUE;
    static int[] ansValue = new int[2];

    static void binarySearch(int s, int e, int key, int[] arr) {
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;

            if (key < 0) {
                // key가 음수인 경우
                if (arr[mid] < 0) {
                    // 비교하려는 후보로 음수가 선택 됐을 때, 해당 후보 왼쪽은 무조건 제거
                    s = mid + 1;
                } else {
                    // 비교하려는 후보로 양수가 선택 됐을 때,
                    // 선택된 key값의 절대값보다 해당 후보가 클 경우 오른쪽 후보들은 제거
                    // 선택된 key값의 절대값보다 해당 후보가 작을 경우 왼쪽 후보들을 제거
                    if(Math.abs(key) < arr[mid]) e = mid - 1;
                    else s = mid + 1;
                }
            } else {
                // key가 양수인 경우
                if (arr[mid] > 0) {
                    // 비교하려는 후보로 양수가 선택 됐을 때, 해당 후보 오른쪽은 무조건 제거
                    e = mid -1;
                } else {
                    // 비교하려는 후보로 음수가 선택 됐을 때,
                    // 선택된 key값의 절대값보다 해당 후보의 절대값이 클 경우 왼쪽 후보들은 제거
                    // 선택된 key값의 절대값보다 해당 후보의 절대값 작을 경우 오른쪽 후보들을 제거
                    if(key > Math.abs(arr[mid])) e = mid - 1;
                    else s = mid + 1;
                }
            }

            if (key != arr[mid] && answer > Math.abs(arr[mid] + key)) {
                answer = Math.abs(arr[mid] + key);
                ansValue[0] = key;
                ansValue[1] = arr[mid];
            }

        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] values = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            binarySearch(0, N-1, values[i], values);
        }

        Arrays.sort(ansValue);
        bw.write(String.valueOf(ansValue[0] + " " + ansValue[1]));
        bw.flush();
        bw.close();
        br.close();
    }
}