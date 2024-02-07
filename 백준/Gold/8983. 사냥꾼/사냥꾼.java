import java.awt.image.BandedSampleModel;
import java.io.*;
import java.util.*;


public class Main {
    static int M, N, L; // 사대의 수, 동물의 수, 사정거리
    static int[] gun; // 사대 위치
    static int[][] animals; // 동물 위치

    static boolean binarySearch(int x, int y) {

        int s = 0, e = M - 1, mid, dist;
        while (s <= e) {
            mid = (s + e) / 2;

            dist = Math.abs(x - gun[mid]) + y;

            if (dist > L) {
                if (x > gun[mid]) s = mid + 1;
                else e = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        gun = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            gun[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(gun);

        animals = new int[N][2];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i][0] = Integer.parseInt(st.nextToken());
            animals[i][1] = Integer.parseInt(st.nextToken());
            if(binarySearch(animals[i][0], animals[i][1])) cnt++;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}