
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 빈도 수를 알아야 함
        // 2. (1) 빈도 수 정렬 , (2) 빈도 수가 같다면 먼저 나온 것이 앞에
        // 3. 빈도 정렬 완성하기

        // * 알아야 할 정보 : 빈도 수, 입력 순서
        // map<숫자, 빈도 수>
        // 정답 배열 ans

        // * 정렬
        // * map의 value값 기준 정렬 => keySet 메소드 활용해서 배열로 변환 후,
            // 해당 배열 값(키값)들을 활용해서 정렬 한다.
        // * 만약 빈도 수가 같다면, 그대로 둠

        // ** 그냥 hashMap 쓰니까 입력 순서대로 값이 저장되지 않는 문제점 발생
        // => LinkedHashMap 사용!!!

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());

            if(map.containsKey(num)) map.replace(num, map.get(num)+1);
            else map.put(num, 1);
        }

        Integer[] keyArr = map.keySet().toArray(new Integer[map.size()]);

        Arrays.sort(keyArr, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                if(map.get(o1) == map.get(o2)) return 0;
                else if(map.get(o1) > map.get(o2)) return -1;
                else return 1;
            }
        });


        int[] ans = new int[N];

        for(int i=0, k=0; i<keyArr.length; i++){
            int num = keyArr[i];
            int cnt = map.get(num);

            for(int j=k, size=k+cnt; j<size; j++,k++){
                ans[j] = num;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int x : ans){
            sb.append(x + " ");
        }
        System.out.println(sb);


    }
}