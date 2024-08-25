import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 걸그룹 수
        int M = Integer.parseInt(st.nextToken());   // 문제 수

        String team; int cnt; String[] members;
        Map<String, String[]> girls = new TreeMap<>();
        Map<String, String> group = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            team = br.readLine();
            cnt = Integer.parseInt(br.readLine());
            members = new String[cnt];
            for (int j = 0; j < cnt; j++) {
                members[j] = br.readLine();
                group.put(members[j], team);
            }
            Arrays.sort(members);
            girls.put(team, members);
        }

        int type; String quiz;
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            quiz = br.readLine();
            type = Integer.parseInt(br.readLine());

            if (type == 0) {
                // 팀 멤버 출력
                for (int i = 0, len = girls.get(quiz).length; i < len; i++) {
                    sb.append(girls.get(quiz)[i]).append("\n");
                }
            } else {
                // 팀 이름 출력
                sb.append(group.get(quiz)).append("\n");
            }
        }
        System.out.println(sb);
    }
}