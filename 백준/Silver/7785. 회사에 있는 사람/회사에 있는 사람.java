
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        // 데이터 중복 X, 순서 보장 X
//        Set<String> set = new HashSet<>();
        // boolean add(E e) : 객체 추가
        // int size : 객체 수 리턴
        // void clear : 저장된 객체 모두 삭제
        // boolean remove(Object o) : 해당 객체 삭제
        // Iterator<E> iterator() : 검색을 위한 반복자 생성
                // Iterator<E> it = set.iterator();
                // it.hashNext() => 데이터가 있으면 true,없으면 false
                // it.next() : 다음 데이터 리턴턴

        // 여기서 TreeSet을 사용할 수도 있다!! => 기본적으로 오름차순 정렬을 지원!!!

        Set<String> set = new TreeSet<>();

       while(n --> 0){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String log = st.nextToken();

            if(log.equals("enter")) set.add(name);
            else set.remove(name);
        }

       // set으로 직접 정렬하려니 안 되었다!! 그래서 배열로 바꿔주고 정렬!
//        String[] ordered = set.toArray(new String[set.size()]);
//       Arrays.sort(ordered, new Comparator<String>(){
//           @Override
//           public int compare(String o1, String o2){
//               return o2.compareTo(o1);
//           }
//       });

       StringBuilder sb = new StringBuilder();
//       for(String name : set){
//           sb.append(name+"\n");
//       }
        String[] ordered = set.toArray(new String[set.size()]);

        for(int i=ordered.length-1; i>=0; i--){
            sb.append(ordered[i]+"\n");
        }
        System.out.println(sb);
    }
}