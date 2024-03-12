import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] balls;
    static int[][] dp;
    static int[][] container;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        balls = new int[3];
        dp = new int[510][510];
        for(int i=0; i<3; i++){
            balls[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(balls);
        container = new int[5][2];
        int flag;
        for (int i = 0; i < 5; i++) {
            st=new StringTokenizer(br.readLine());
            container[i][0]=Integer.parseInt(st.nextToken());
            container[i][1]=Integer.parseInt(st.nextToken());
            flag = dp(container[i][0],container[i][1]);
            System.out.println(flag==1?"A":"B");
        }
    }

    // 2는 B가 승리하는 경우 1은 A가 승리하는 경우 0은 아직 방문이 안된 dp
    private static int dp(int k1, int k2) {
        if(k1<balls[0] && k2 <balls[0]) return 2;

        if(dp[k1][k2]!=0){
            return dp[k1][k2];
        }

        int result =2;

        for(int i=0; i<3; i++){
            if(k1>=balls[i])
                if(dp(k1-balls[i],k2)==2)result=1;
            if(k2>=balls[i])
                if(dp(k1,k2-balls[i])==2)result=1;
        }
        dp[k1][k2]=result;
        return result;
    }
}