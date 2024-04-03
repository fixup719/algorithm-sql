import java.io.*;
import java.util.*;


public class Main {
    static int N, T;
    static int[][] stones;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    // N이 50000개까지 가능
    // for문을 전부 탐색하지말고 이동 가능한 부분만 탐색하게 바꾸기


    static int bfs(int y, int i) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, i});
        visited[i] = true;

        int cy, ci, ny, size, dist = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cy = q.peek()[0];
                ci = q.poll()[1];

                if (cy == T) return dist;

                for (Integer ni : list[ci]) {
                    if (visited[ni]) continue;

                    ny = stones[ni][1];
                    q.offer(new int[]{ny, ni});
                    visited[ni] = true;
                }
            }
            dist++;
        }

        return -1;
    }

    // 이동 가능한 범위 중 가장 작은 인덱스 값 출력
    static int lowerBound(int s, int e, int key) {
        int m;
        int answer = e;

        while (s <= e) {
            m = (s + e) / 2;
            if (stones[m][0] < stones[key][0] - 2) {
                // 현재 중간값의 x가 이동할 수 있는 x값 보다 작은 값일 때
                s = m + 1;
            } else if (stones[key][0] + 2 < stones[m][0]) {
                // 현재 중간값의 x가 이동할 수 있는 x값 보다 큰 값일 떄
                e = m - 1;
            } else {
                // x간 이동이 가능 함
                answer = m;
                // 하지만 이동 가능한 좌표들 중 가장 작은 값을 구할 것이므로
                e = m - 1;
            }
        }
        return answer;
    }

    // 이동 가능한 범위중 가장 큰 인덱스값 출력
    static int upperBound(int s, int e, int key) {
        int m;
        int answer = e;

        while (s <= e) {
            m = (s + e) / 2;
            if (stones[m][0] < stones[key][0] - 2) {
                // 현재 중간값의 x가 이동할 수 있는 x값 보다 작은 값일 때
                s = m + 1;
            } else if (stones[key][0] + 2 < stones[m][0]) {
                // 현재 중간값의 x가 이동할 수 있는 x값 보다 큰 값일 떄
                e = m - 1;
            } else {
                // x간 이동이 가능 함
                answer = m;
                // 하지만 이동 가능한 좌표들 중 가장 작은 값을 구할 것이므로
                s = m + 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        stones = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            stones[i][0] = Integer.parseInt(st.nextToken());
            stones[i][1] = Integer.parseInt(st.nextToken());
        }

        // x좌표 기준 정렬
        Arrays.sort(stones, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];});

        // 위 두 범위를 모두 충족하는 좌표만 리스트에 담기
        int lowerX, upperX;
        for (int i = 0; i <= N; i++) {
            lowerX = lowerBound(0, N, i);
            upperX = upperBound(0, N, i);

            for (int j = lowerX; j <= upperX ; j++) {
                if (Math.abs(stones[i][1] - stones[j][1]) <= 2) {
                    list[i].add(j);
                }
            }
        }

        visited = new boolean[N + 10];

        bw.write(String.valueOf(bfs(0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }
}