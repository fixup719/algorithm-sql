

import java.io.*;
import java.util.*;


public class Main {

    /*
        L : 커서 왼쪽 한 칸(맨 앞이면 무시)
        D : 커서 오른쪽 한 칸(맨 뒤면 무시)
        B : 커서 왼쪽 문자 삭제(맨 앞이면 무시)
        P $ : $라는 문자를 커서 왼쪽에 추가

        1. 리스트에 문자열 삽입 list
        2. 초기 커서 위치는 맨 뒤 idx = list.size()
        3. 명령어 수행
            3-1. L
                if) 커서 위치가 맨 앞인가? idx == 0 continue
                else) idx--
            3-2. D
                if) 커서 위치가 맨 뒤인가? idx == list.size() continue
                else) idx++
            3-3. B
                if) 커서 위치가 맨 앞인가? idx == 0 continue
                else) list.remove(--idx);
            3-4. P $
                list.add(idx++, $);
     * */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        List<Character> list = new LinkedList<>();
        for(int i=0, len=str.length(); i<len; i++){
            list.add(str.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());
        int idx = list.size();
        ListIterator<Character> iterator = list.listIterator(list.size());
        // list.size()를 주면 젤 뒤에 커서가 위치하는 형태가 된다.
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("P")){
                char ch = st.nextToken().charAt(0);
                iterator.add(ch);
            }else if(cmd.equals("L")){
                if(iterator.hasPrevious()) iterator.previous();
            }else if(cmd.equals("D")){
                if(iterator.hasNext()) iterator.next();
            }else{
                if(iterator.hasPrevious()) {
                    iterator.previous();
                    iterator.remove();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character x : list){
            sb.append(x);
        }

        System.out.println(sb);
    }
}