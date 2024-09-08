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

        // 작은 사각형에서 대각선이 지나는 타일 개수 구하기
        // 작은사각형의 크기 x, y로 갱신
        y /= boxCnt;
        x /= boxCnt;
        // 대각선을 그었을 때
        // 가로선은 y-1개만큼, 세로선은 x-1개만큼 지난다
        // 따라서 대각선이 지나는 타일 개수는 x-1 + y-1 + 1이 된다.
        System.out.println(boxCnt * (x + y - 1));

    }
}

