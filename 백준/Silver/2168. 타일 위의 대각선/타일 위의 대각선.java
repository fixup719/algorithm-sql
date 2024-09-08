import java.io.*;
import java.util.*;

public class Main {

    static int getGCD(int a, int b) {
        int temp = 0;
        while (a % b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }

        return b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        // 대각선을 이을 때, 정점과 정점을 잇는 가장 작은 사각형 개수 찾기
        int boxCnt = getGCD(x, y);
        // 작은 사각형의 끝 정점(대각선을 이을 때 최초로 만나는 정점)
        int mx = x / boxCnt;
        int my = y - (y / boxCnt);
        // 직선그래프 기울기
        double a = - ((double)y / x);
        // 직선 그래프 y절편
        int b = y;

        // 작은 사각형에서 대각선이 지나는 사각형 개수 구하기
        int sum = 0; double ty, py = y - 1;
        for (int tx = 1; tx <= mx; tx++) {
            ty = a * tx + b;
            sum += (int) py - (int) ty + 1;
            py = ty;
        }


        System.out.println(sum * boxCnt);

    }
}

