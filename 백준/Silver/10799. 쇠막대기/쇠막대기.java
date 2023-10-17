

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int cnt = 0;

        Deque<Character> stack = new ArrayDeque<>();
        boolean isOpen = false;

        for(int i=0, len = str.length(); i<len; i++){
            char ch = str.charAt(i);


            if(ch=='('){
                isOpen = true;
                stack.addLast(ch);
            }else if(ch==')'){
                if(!stack.isEmpty()){
                    // 사실 체크할 필요는 없긴 함
                    stack.removeLast();
                    if(isOpen){
                        // 래이저
                        cnt += stack.size();
                    }else{
                        // 쇠 막대기
                        cnt += 1;
                    }
                }
                isOpen = false;
            }



        }

        System.out.println(cnt);

    }
}