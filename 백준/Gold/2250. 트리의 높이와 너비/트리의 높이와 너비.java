import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] list;
    static int[] parent;
    static int root;
    static ArrayList<Integer>[] posY;
    static int[] posX;
    static int x = 1;

    static void inorder(int cur, int y) {
        int leftNode = list[cur].get(0);
        int rightNode = list[cur].get(1);

        // 행 위치 기록
        posY[y].add(cur);

        if (leftNode != -1) inorder(leftNode, y + 1);
        // 열 위치 기록
        posX[cur] = x++;
        if (rightNode != -1) inorder(rightNode, y + 1);
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int ro, le, ri;
        parent = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ro = Integer.parseInt(st.nextToken());
            le = Integer.parseInt(st.nextToken());
            ri = Integer.parseInt(st.nextToken());

            list[ro].add(le);
            list[ro].add(ri);

            // 부모 노드 기록하기 -> 여기서 주의
            if (le != -1) parent[le] = ro;
            if (ri != -1) parent[ri] = ro;
        }

        // 최상위 뿌리 노드 찾기
        for (int i = 1; i <= N; i++) {
            if (parent[i] == 0) {
                root = i;
                break;
            }
        }

        // x,y좌표 기록하기
        posY = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            posY[i] = new ArrayList<>();
        }
        posX = new int[N + 1];
        inorder(root, 1);

        int max = 0, level = 0, lidx, ridx, dist;
        for (int i = 1; i < N + 1; i++) {
            if (posY[i].isEmpty()) break;

            lidx = posY[i].get(0);
            ridx = posY[i].get(posY[i].size() - 1);

            dist = posX[ridx] - posX[lidx] + 1;

            if (max < dist) {
                max = dist;
                level = i;
            }
        }

        bw.write(level + " " + max);
        bw.flush();
        bw.close();
        br.close();
    }
}