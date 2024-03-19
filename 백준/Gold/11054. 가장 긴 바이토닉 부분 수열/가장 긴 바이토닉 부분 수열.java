import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
        * 증가하거나, 감소하거나, 증가감소하는 부분수열중 최대 길이 구하기
        * */

        int N = Integer.parseInt(br.readLine()); // 아이들 수

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int[] dp1 = new int[N]; // 증가
        int[] dp2 = new int[N]; // 감소
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) dp1[i] = Math.max(dp1[i], dp1[j] + 1);

            }
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= i; j--) {
                if (arr[i] > arr[j]) dp2[i] = Math.max(dp2[i], dp2[j] + 1);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp1[i] + dp2[i]);
        }

//        System.out.println(Arrays.toString(dp1));
//        System.out.println(Arrays.toString(dp2));

        bw.write(String.valueOf(max - 1));
        bw.flush();
        bw.close();
        br.close();
    }
}