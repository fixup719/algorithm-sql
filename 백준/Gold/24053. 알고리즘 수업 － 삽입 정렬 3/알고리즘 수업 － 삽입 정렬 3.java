import java.io.*;
import java.util.*;

public class Main {

    static boolean isSame(int[] A, int[] B) {
        int N = A.length;
        boolean ans = true;
        for (int i = 0; i < N; i++) {
            if (A[i] != B[i]) ans = false;
        }

        return ans;
    }

    static int insert_sort(int[] A, int[] B) {
        int N = A.length, loc, newItem, tmp; int cnt = 0;
        for (int i = 1; i < N; i++) {
            loc = i - 1;
            newItem = A[i];

            while (0 <= loc && newItem < A[loc]) {
                if (isSame(A, B)) cnt++;
                A[loc + 1] = A[loc];
                if (isSame(A, B)) cnt++;
                loc--;
            }

            if (loc + 1 != i) A[loc + 1] = newItem;
            if (isSame(A, B)) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int[] B = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int ret = insert_sort(A, B);
        if (ret > 0) System.out.println(1);
        else System.out.println(0);
    }
}

