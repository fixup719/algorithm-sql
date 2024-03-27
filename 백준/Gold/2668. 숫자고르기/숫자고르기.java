import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static ArrayList<Integer> list = new ArrayList<>();

    static void dfs(int node, int start) {

        if (arr[node] == start) {
            list.add(start);
        }

        if (!visited[arr[node]]) {
            visited[arr[node]] = true;
            dfs(arr[node], start);
            visited[arr[node]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(list);
        
        StringBuilder sb = new StringBuilder();
        sb.append(list.size() + "\n");
        for (Integer i : list) {
            sb.append(i+"\n");
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}