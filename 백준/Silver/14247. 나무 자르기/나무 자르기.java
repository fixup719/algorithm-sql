import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Tree implements Comparable<Tree> {
        int idx;
        int grow;

        public Tree(int idx, int grow) {
            this.idx = idx;
            this.grow = grow;
        }

        @Override
        public int compareTo(Tree o) {
            if(this.grow == o.grow) return this.idx - o.idx;
            else return this.grow - o.grow;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Tree[] growUp = new Tree[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            growUp[i] = new Tree(i, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(growUp);

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (trees[growUp[i].idx] + (long) i * growUp[i].grow);
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}