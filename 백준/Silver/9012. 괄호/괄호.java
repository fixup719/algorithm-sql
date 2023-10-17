

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;

        Deque<Character> stack = new ArrayDeque<>();

        int T = Integer.parseInt(br.readLine());
        boolean check;

        while(T-->0){
            String cmd = br.readLine();
            check = true;

            for(int i=0, len=cmd.length(); i<len; i++){
                char input = cmd.charAt(i);

                if(input=='('){
                    // 열린 괄호
                    stack.addLast(input);
                }else if(input==')'){
                    // 닫힌 괄호
                    if(!stack.isEmpty()) stack.removeLast();
                    else {
                        check = false;
                        break;
                    }
                }
            }

            if(!stack.isEmpty()) {
                check=false;
                stack.clear();
            }

            if(check) System.out.println("YES");
            else System.out.println("NO");
        }


    }
}