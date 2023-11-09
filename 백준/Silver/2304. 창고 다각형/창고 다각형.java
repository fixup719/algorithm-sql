import java.io.*;
import java.util.*;

public class Main {

    static class Poll implements Comparable<Poll>{
        int x;
        int y;

        Poll(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Poll o){
            return x-o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Poll[] polls = new Poll[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            polls[i] = new Poll(x, y);
        }

        Arrays.sort(polls);
        int maxH = 0;
        int maxIdx = 0;
        for(int i=0; i<N; i++){
            Poll p = polls[i];
            if(maxH <= p.y){
                maxH = p.y;
                maxIdx = i;
            }
        }

        int sum = 0;
        int curL=polls[0].x, curH=polls[0].y;
        for(int i=0; i<=maxIdx; i++){
            Poll p = polls[i];

            if(curH <= p.y){
                sum += (p.x-curL)*curH;
                curL = p.x;
                curH = p.y;
            }
        }


        curL=polls[N-1].x;
        curH=polls[N-1].y;
        for(int i=N-1; i>=maxIdx; i--){
            Poll p = polls[i];

            if(curH <= p.y){
                sum += (curL-p.x)*curH;
                curL = p.x;
                curH = p.y;
            }
        }

        System.out.println(sum + maxH);
    }
}