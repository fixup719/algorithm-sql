import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = Integer.MIN_VALUE; // 정답(부분 수열의 최종 최대값)
        int sumMax = 0; // 부분 수열 합계 중 최대값
        int sum = 0; // sumMax와 배열의 원소 숫자와 더한 값
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            sum = sumMax + num;

            if(sum < num) sumMax = num;
            else sumMax = sum;

            if(answer < sumMax) answer = sumMax;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}