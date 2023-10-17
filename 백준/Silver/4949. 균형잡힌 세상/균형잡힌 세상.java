

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
        boolean check = true;
        while(true){
            String str = br.readLine();

            if(str.equals("."))  break;


            for(int i=0, len=str.length(); i<len; i++){
                char ch = str.charAt(i);

                if(ch=='(' || ch=='[') stack.addLast(ch);
                else if(ch==')'){
                    if(!stack.isEmpty() && stack.peekLast()=='(') stack.removeLast();
                    else {
                        check=false;
                        break;
                    }
                }
                else if(ch==']'){
                    if(!stack.isEmpty() && stack.peekLast()=='[') stack.removeLast();
                    else {
                        check=false;
                        break;
                    }
                }
            }


            if(!stack.isEmpty()) check=false;

            if(check) System.out.println("yes");
            else {
                System.out.println("no");
                stack.clear();
                check = true;
            }
        }
    }
}