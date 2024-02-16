import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int k;
    static String[] signs;
    static int[] arr;
    static ArrayList<String> answer = new ArrayList<>();
    static boolean[] visited;

    static boolean check(int cur) {

        if (cur <= 1) return true;

        if (cur > 1) {
            if (signs[cur - 2].equals("<") && arr[cur - 2] >= arr[cur - 1]) return false;
            if (signs[cur - 2].equals(">") && arr[cur - 2] <= arr[cur - 1]) return false;
        }

        return true;
    }
    static void recur(int cur) {

        if (!check(cur)) return;

        if (cur == k + 1) {
            String str = "";
            for (int i = 0; i < k + 1; i++) {
                str += String.valueOf(arr[i]);
            }
            answer.add(str);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cur] = i;
            recur(cur + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        k = Integer.parseInt(br.readLine());
        signs = br.readLine().split(" ");

        arr = new int[k + 1];
        visited = new boolean[10];

        recur(0);

        Collections.sort(answer);

        sb.append(answer.get(answer.size()-1)+"\n");
        sb.append(answer.get(0));

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}