import java.io.*;
import java.util.*;

public class Main {
    static int a, b;
    static boolean[][] visited = new boolean[1000010][2];
    static int[] mul = {1, 2, 10};
    static int[] plus = {1, 0, 0};

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a, 1});
        visited[a][1] = true;

        int cur, chance, nxt, size, cnt = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cur = q.peek()[0];
                chance = q.poll()[1];

                if (cur == b) {
                    return cnt;
                }

                for (int i = 0; i < 2 + chance; i++) {
                    nxt = mul[i] * cur + plus[i];

                    if (nxt > b) continue;

                    if (i == 2) {
                        if (!visited[nxt][chance - 1]) {
                            visited[nxt][chance - 1] = true;
                            q.offer(new int[]{nxt, chance - 1});
                        }
                    } else {
                        if (!visited[nxt][chance]) {
                            visited[nxt][chance] = true;
                            q.offer(new int[]{nxt, chance});
                        }
                    }
                }
            }

            cnt++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        System.out.println(bfs());

        br.close();
    }
}