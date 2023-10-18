

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];

        // treeSet 이용해서 정렬하면서 set 입력받기
        Set<Integer> set = new TreeSet<>();
        for(int i=0; i<N; i++){
            int number = Integer.parseInt(st.nextToken());
            nums[i] = number;
            set.add(number);
        }

        // 좌표 압축 기록 map<value,압축값>
        Map<Integer, Integer> sortedIdx = new HashMap<>();
        int idx=0;
        for(int x: set) sortedIdx.put(x,idx++);

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(sortedIdx.get(nums[i]) + " ");
        }
        System.out.println(sb);
    }
}