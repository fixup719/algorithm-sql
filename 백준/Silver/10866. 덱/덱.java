import java.io.*;
import java.util.*;

public class Main {
    static int[] deque = new int[20010];
    static int size = 0;

    static void pushFront(int x) {
        int tmp1 = deque[0];
        int tmp2;
        size++;
        for (int i = 0; i < size - 1; i++) {
            tmp2 = deque[i + 1];
            deque[i + 1] = tmp1;
            tmp1 = tmp2;
        }

        deque[0] = x;
    }

    static void pushBack(int x) {
        deque[size] = x;
        size++;
    }

    static int popFront() {
        if (size == 0) {
            return -1;
        } else {
            int num = deque[0];

            for (int i = 0; i < size - 1; i++) {
                deque[i] = deque[i + 1];
            }

            size--;
            return num;
        }
    }

    static int popBack() {
        if (size == 0) {
            return -1;
        } else {
            int num = deque[size - 1];
            size--;
            return num;
        }
    }

    static int getSize() {
        return size;
    }

    static int isEmpty() {
        if (size == 0) return 1;
        else return 0;
    }

    static int getFront() {
        if (size == 0) {
            return -1;
        } else {
            return deque[0];
        }
    }

    static int getBack() {
        if (size == 0) {
            return -1;
        } else {
            return deque[size - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int x;
        String cmd;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();

            if (cmd.equals("push_front")) {
                x = Integer.parseInt(st.nextToken());
                pushFront(x);
            } else if (cmd.equals("push_back")) {
                x = Integer.parseInt(st.nextToken());
                pushBack(x);
            } else if (cmd.equals("pop_front")) {
                sb.append(popFront() + "\n");
            } else if (cmd.equals("pop_back")) {
                sb.append(popBack() + "\n");
            } else if (cmd.equals("size")) {
                sb.append(getSize() + "\n");
            } else if (cmd.equals("empty")) {
                sb.append(isEmpty() + "\n");
            } else if (cmd.equals("front")) {
                sb.append(getFront() + "\n");
            } else if (cmd.equals("back")) {
                sb.append(getBack() + "\n");
            }

        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}