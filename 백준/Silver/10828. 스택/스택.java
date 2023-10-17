

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Deque<Integer> stack = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());
        while(N --> 0){
            st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("push")){
                int value = Integer.parseInt(st.nextToken());
                stack.addLast(value);
            }else if(cmd.equals("pop")){
                if(!stack.isEmpty()) {
                    System.out.println(stack.removeLast());
                } else System.out.println(-1);
            }else if(cmd.equals("size")){
                System.out.println(stack.size());
            }else if(cmd.equals("empty")){
                if(stack.isEmpty()) System.out.println(1);
                else System.out.println(0);
            }else if(cmd.equals("top")){
                if(!stack.isEmpty()) System.out.println(stack.peekLast());
                else System.out.println(-1);
            }

        }

    }
}