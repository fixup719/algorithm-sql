
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[101][101];
        int s = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for(int r=y; r<y+10; r++){
                for(int c=x; c<x+10; c++){
                    if(!visited[r][c]) s++;
                    visited[r][c] = true;
                }
            }
        }

        System.out.println(s);

    }
}
