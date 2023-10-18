

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        // Java map
        // key를 통해 value를 얻는다는 특징이 있음
            // key: 중복X, value: 중복O
        // 요소의 저장 순서 유지X
        // 종류
            // - HashMap
            // - HashTable
            // - TreeMap :  입력된 key의 sort 순으로 데이터 출력
            // - LinkedHashMap : 입력된 순서대로 데이터 출력
        // 메소드
            // V get(Object key) : value
            // boolean containsKey(key값) : 해당 키값이 존재하면 true, 없으면 false
            // V remove(Object key) : 키값에 해당하는 아이템 삭제하고, 해당 값 return
            // int size() : 사이즈 출력
            // void clear() : 해당 맵의 모든 매핑 제거
            // boolean containsValue(value값)
            // boolean isEmpty()
            // V put(K key, V value)
            // V replace(K key, V value) : 해당 맵에서 전달된 키에 대응하는 값을 특정 값으로 대체
            // boolean replace(K key, V oldValue, V newValue) : 해당 맵에서 특정 값에 대응하는 전달된 키의 값을 새로운 값으로 대체
            // entrySet() => key, value 모든 값 출력
            // keySet() => key 값 출력

        while(N-->0){
            String title = br.readLine();
            if(map.containsKey(title)){
                map.replace(title, map.get(title)+1);
            }else{
                map.put(title, 1);
            };
        }

        int maxCnt = 0;
        String maxTitle = "";
        for(Map.Entry<String,Integer> book : map.entrySet()){
            String titleName = book.getKey();
            int count = book.getValue();

            if(count > maxCnt || (count==maxCnt && titleName.compareTo(maxTitle)<0)){
                maxTitle=titleName;
                maxCnt = count;
            }
        }

        System.out.println(maxTitle);

    }
}