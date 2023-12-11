
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] numbers;
    static boolean isEnd = false;
    /*
        <로직 생각>
        - dfs로 접근
        - 연속되면 X
        - depth가 N이 될때까지
        - 방문체크 1~depth/2-1 이전까지 ~ 2*1+1~2*depth/2+1 방문체크 비교
        - 방문체크 비교시 같은게 등장할때마다 count+1 그리고 그값이 비교한 숫자갯수랑 같으면 나쁜수열이라 판단
        - 한 턴 끝날 때마다 visited false로 바꾸기
        - 가장 작은 수는 숫자 순서대로 123으로 하면 됨
        => 근데 생각해보니 굳기 visited 배열 쓸 필요 없을 듯...? 그냥 ans배열 사용해도 될 것 같다..
        또, 어차피 123 순서대로 넣게되면 최초로 나오는 수열이 가장 작은 수열 아님..?


        - 와웅.... substring이라는 메소드 쓰면 되는구나....????
        - 직접 구현해볼까???
    */

    static void dfs(int depth){

        if(depth == N){
            isEnd = true;
            return;
        }

        for(int i=1; i<=3; i++){

            if(isEnd) return; // 최초로 등장한 수가 가장 작은 수이므로 종료!!!

            numbers[depth] = i;
            if(!isSame(depth)){
               // 좋은 수열이면
                dfs(depth+1);
            }
        }

    }

    static boolean isSame(int depth){
        boolean answer = false;

        for(int k=1; k<=(depth+1)/2; k++){
            int cnt = 0;
            for(int i=0; i<k; i++){
                if(numbers[depth-k-i] == numbers[depth-i]) cnt++;
            }
            if(cnt == k) {
                return true;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N];

        dfs(0);

        StringBuilder sb = new StringBuilder();
        for(int num : numbers){
            sb.append(num);
        }
        System.out.println(sb);

    }

}