import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int A, B;
    static int[][] arr;
    static int answer = 0;

    static boolean binarySearch(int x1, int y1) {
        int s = 0, e = N - 1, mid, x2, y2;
        while (s <= e) {
            mid = (s + e) / 2;

            x2 = arr[mid][0];
            y2 = arr[mid][1];

            if (x1 < x2 || (x1 == x2 && y1 < y2)) {
                e = mid - 1;
            } else if (x1 > x2 || (x1 == x2 && y1 > y2)) {
                s = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        for (int i = 0; i < N; i++) {
            if (binarySearch(arr[i][0] + A, arr[i][1] + B)
                    && binarySearch(arr[i][0] + A, arr[i][1])
                    && binarySearch(arr[i][0],arr[i][1] + B)) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}