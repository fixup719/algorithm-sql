

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        LinkedList<Integer> list= new LinkedList<>();

        for(int i=1; i<=N; i++){
            list.add(i);
        }

        // 체크할 것
        // 1. 시작 접근 index = K
        // 2. index와 list size 비교
            // 2-1. index>=list.size() 라면 index = lindex-ist.size()
        // 3. 접근 후 해당 숫자 노드 제거
        // 4. index 갱신 +(K-1)

        int idx = K-1;
        int size;
        while((size = list.size())!=0){

            while(idx >= size){
                idx = idx-size;
            }

            if(size>1) sb.append(list.get(idx) + ", ");
            else sb.append(list.get(idx));

            list.remove(idx);

            idx += (K-1);
        }


        sb.append(">");
        System.out.println(sb);
    }
}