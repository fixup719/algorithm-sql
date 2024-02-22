import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int[][] copyArr;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    static boolean check() {
        int total;

        for (int i = 0; i < 3; i++) {
            total = 0;

            for (int j = 0; j < 3; j++) {
                total += copyArr[i][j];
            }

            if(total != 15) return false;
        }

        for (int i = 0; i < 3; i++) {
            total = 0;

            for (int j = 0; j < 3; j++) {
                total += copyArr[j][i];
            }

            if(total != 15) return false;
        }

        if(copyArr[0][0] + copyArr[1][1] + copyArr[2][2] != 15) return false;

        if(copyArr[2][0] + copyArr[1][1] + copyArr[0][2] != 15) return false;

        return true;
    }

    static void recur(int x, int y, int cost) {
        if (y == 3) {
            x++;
            y = 0;
        }

        if (x == 3) {
            if(!check()) return;

            answer = Math.min(answer, cost);

            return;
        }

        for (int i = 1; i <= 9; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            copyArr[x][y] = i;
            recur(x, y + 1, cost + Math.abs(arr[x][y] - i));
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        copyArr = new int[3][3];
        visited = new boolean[10];
        recur(0, 0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}