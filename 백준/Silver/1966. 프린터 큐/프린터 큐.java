

import java.io.*;
import java.util.*;


public class Main {

    public static class El{
        boolean selected;
        int priorNum;

        El(boolean selected, int priorNum){
            this.selected = selected;
            this.priorNum = priorNum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        while(TC-->0){

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<El> q = new LinkedList<>();
            Integer[] priorities = new Integer[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                int el = Integer.parseInt(st.nextToken());
                q.offer(new El(i==M?true:false, el));
                priorities[i] = el;
            }
            Arrays.sort(priorities, Collections.reverseOrder());

            int ans=1;
            int idx=0;
            while(!q.isEmpty()){
                int p = priorities[idx];
                int el = q.peek().priorNum;
                boolean check = q.poll().selected;

                if(el<p){
                    q.offer(new El(check, el));
                }else if(el==p){
                    idx++;
                    if(check){
                        System.out.println(ans);
                        break;
                    }
                    ans++;
                }

            }

        }
    }
}