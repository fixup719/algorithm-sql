import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[] arr = new String[C];
        Arrays.fill(arr, "");
        String str;
        for (int i = 0; i < R; i++) {
            str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[j] += str.charAt(j);
            }
        }

        Set<String> set = new TreeSet<>();
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                set.add(arr[j].substring(i));
            }
            if (set.size() < C) break;
            cnt++;
            set.clear();
        }

        bw.write(String.valueOf(cnt-1));
        bw.flush();
        bw.close();
        br.close();
    }
}