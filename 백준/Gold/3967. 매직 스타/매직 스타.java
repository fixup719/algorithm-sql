import java.io.*;

public class Main {
    static char[][] map;
    static boolean[] visited;
    static char[] star;
    static char[] chars = {0, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
    static int[][] sumIdx = {{1, 2, 3, 4}, {7, 8, 9, 10}, {1, 5, 8, 11}
            , {4, 6, 9, 11}, {0, 2, 5, 7}, {0, 3, 6, 10}};
    static boolean isEnd;
    static StringBuilder sb = new StringBuilder();

    static boolean check() {
        int sum;
        for (int i = 0; i < 6; i++) {
            sum = 0;
            for (int j = 0; j < 4; j++) {
                sum += star[sumIdx[i][j]] - 64;
            }

            if(sum != 26) return false;
        }
        return true;
    }

    static void recur(int cur) {

        if(isEnd) return;

        if (cur == 12) {

            if (check()) {
                for (int i = 0, k = 0; i < 5; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (map[i][j] != '.') {
                            map[i][j] = star[k++];
                        }
                        sb.append(map[i][j]);
                    }
                    sb.append("\n");
                }
                isEnd =  true;
            }


            return;
        }

        if (cur < 12 && star[cur] != 'x') {
            recur(cur + 1);
        } else {
            for (int i = 1; i <= 12; i++) {
                if(visited[i]) continue;
                visited[i] = true;
                star[cur] = chars[i];
                recur(cur + 1);
                visited[i] = false;
                star[cur] = 'x';
            }
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // A : 65

        map = new char[5][9];
        visited = new boolean[13];
        star = new char[12];
        String str;
        for (int i = 0, k = 0; i < 5; i++) {
            str = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j);
                if (0 <= map[i][j] - 'A' && map[i][j] - 'A' < 12 || map[i][j] == 'x') {
                    if (map[i][j] != 'x') {
                        visited[map[i][j] - 'A' + 1] = true;
                    }
                    star[k] = map[i][j];
                    k++;
                }
            }
        }

        recur(0);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}