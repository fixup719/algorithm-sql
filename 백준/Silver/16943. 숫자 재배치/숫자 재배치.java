import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int A, B;
    static ArrayList<Integer> arr = new ArrayList<>();
    static int size;
    static boolean[] visited;
    static int[] selected;
    static int answer = -1;

    static void recur(int cur) {

        if (cur == size) {

            if(arr.get(selected[0]) == 0) return;

            int num = 0;
            for (int i = size - 1, j = 1; i >= 0; i--) {
                num += arr.get(selected[i]) * j;
                j *= 10;
            }

            if(num > B) return;

            answer = Math.max(answer, num);

            return;
        }

        for (int i = 0; i < size; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[cur] = i;
            recur(cur + 1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int num = A;
        while (num > 0) {
            arr.add(num % 10);
            num /= 10;
        }

        size = arr.size();
        visited = new boolean[size];
        selected = new int[size];

        recur(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}