import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Node[] lst;

    static class Node {
        int left;
        int right;

        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static void preOrder(int cur) {
        System.out.print((char)(cur + 'A'));
        int left = lst[cur].left;
        if (left != -1) {
            preOrder(left);
        }
        int right = lst[cur].right;
        if (right != -1) {
            preOrder(right);
        }
    }

    static void inOrder(int cur) {
        int left = lst[cur].left;
        if (left != -1) {
            inOrder(left);
        }
        System.out.print((char)(cur + 'A'));
        int right = lst[cur].right;
        if (right != -1) {
            inOrder(right);
        }
    }

    static void postOrder(int cur) {
        int left = lst[cur].left;
        if (left != -1) {
            postOrder(left);
        }
        int right = lst[cur].right;
        if (right != -1) {
            postOrder(right);
        }
        System.out.print((char)(cur + 'A'));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lst = new Node[N + 1];

        int v, lv, rv; String tmp;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            v = st.nextToken().charAt(0) - 'A';
            tmp = st.nextToken();
            if (!tmp.equals(".")) {
                lv = tmp.charAt(0) - 'A';
            } else {
                lv = -1;
            }
            tmp = st.nextToken();
            if (!tmp.equals(".")) {
                rv = tmp.charAt(0) - 'A';
            } else {
                rv = -1;
            }
            lst[v] = new Node(lv, rv);
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
    }
}

