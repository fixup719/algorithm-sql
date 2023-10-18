

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        int lastVal = 0;
        while(N --> 0){

            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("push")){
                int number = Integer.parseInt(st.nextToken());
                lastVal = number;
                q.offer(number);
            }else if(cmd.equals("pop")){
                if(!q.isEmpty()) System.out.println(q.poll());
                else System.out.println(-1);
            }else if(cmd.equals("size")){
                System.out.println(q.size());
            }else if(cmd.equals("empty")) {
                if(!q.isEmpty()) System.out.println(0);
                else System.out.println(1);
            }else if(cmd.equals("front")){
                if(!q.isEmpty()) System.out.println(q.peek());
                else System.out.println(-1);
            }else if(cmd.equals("back")){
                if(!q.isEmpty()) System.out.println(lastVal);
                else System.out.println(-1);
            }
        }

    }
}