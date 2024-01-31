import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()) + 500000;
            arr[i][1] = Integer.parseInt(st.nextToken()) + 500000;
        }

        int[] prefixX = new int[1000010];
        for (int i = 1; i < n; i++) {
            if (arr[i - 1][0] < arr[i][0]) {
                prefixX[arr[i - 1][0]] += 1;
                prefixX[arr[i][0]] += -1;
            } else if (arr[i - 1][0] > arr[i][0]) {
                prefixX[arr[i - 1][0]] += -1;
                prefixX[arr[i][0]] += 1;
            }
        }
        // 첫번쨰랑 마지막 꼭짓점 연결하는 부분 추가!
        if (arr[0][0] < arr[n - 1][0]) {
            prefixX[arr[0][0]] += 1;
            prefixX[arr[n - 1][0]] += -1;
        } else if (arr[0][0] > arr[n - 1][0]) {
            prefixX[arr[0][0]] += -1;
            prefixX[arr[n - 1][0]] += 1;
        }

        int[] prefixY = new int[1000010];
        for (int i = 1; i < n; i++) {
            if (arr[i - 1][1] < arr[i][1]) {
                prefixY[arr[i - 1][1]] += 1;
                prefixY[arr[i][1]] += -1;
            } else if (arr[i - 1][1] > arr[i][1]) {
                prefixY[arr[i - 1][1]] += -1;
                prefixY[arr[i][1]] += 1;
            }
        }
        // 첫번쨰랑 마지막 꼭짓점 연결하는 부분 추가!
        if (arr[0][1] < arr[n - 1][1]) {
            prefixY[arr[0][1]] += 1;
            prefixY[arr[n - 1][1]] += -1;
        } else if (arr[0][1] > arr[n - 1][1]) {
            prefixY[arr[0][1]] += -1;
            prefixY[arr[n - 1][1]] += 1;
        }

        int xMax = 0;
        for (int i = 1; i < 1000010; i++) {
            prefixX[i] += prefixX[i - 1];
            if(xMax < prefixX[i]) xMax = prefixX[i];
        }

        int yMax = 0;
        for (int i = 1; i < 1000010; i++) {
            prefixY[i] += prefixY[i - 1];
            if(yMax < prefixY[i]) yMax = prefixY[i];
        }

        bw.write(String.valueOf(Math.max(xMax, yMax)));
        bw.flush();
        bw.close();
        br.close();
    }
}