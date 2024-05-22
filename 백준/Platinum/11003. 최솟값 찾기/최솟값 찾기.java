import com.sun.security.jgss.GSSUtil;
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;


public class Main {

    static class Num {
        int no;
        int idx;

        Num(int no, int idx) {
            this.no = no;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Num> deq = new ArrayDeque<>();
        deq.offer(new Num(arr[1], 1));
        StringBuilder sb = new StringBuilder();
        sb.append(arr[1]).append(" ");
        int size;
        for (int i = 2; i <= N; i++) {
            // 가장 앞에 있는 원소의 인덱스가 L범위를 벗어나면 제거
            if (!deq.isEmpty() && deq.peekFirst().idx < i - L + 1) deq.removeFirst();

            // 만약 덱에 가장 뒤에있는 원소부터 탐색하면서 현재 원소보다 큰 값들은 제거
            size = deq.size();
            while (size > 0) {
                if (!deq.isEmpty() && deq.peekLast().no >= arr[i]) deq.removeLast();
                else break;
                size--;
            }

            // 덱에 현재 원소 넣기
            deq.addLast(new Num(arr[i], i));

            sb.append(deq.peekFirst().no).append(" ");
        }

        System.out.println(sb);

        br.close();
    }
}