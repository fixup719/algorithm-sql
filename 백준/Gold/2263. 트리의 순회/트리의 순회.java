import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] inorder;
    static int[] postorder;
    static int[] inorderIdx;
    static StringBuilder sb = new StringBuilder();

    /*
     인오더, 포스트오더 모두 가장 왼쪽 서브트리 노드부터 방문한다.(왼쪽 서브트리 노드 존재할 경우 첫번쨰 노드가 같음)
     인오더 : 왼 -> 루 -> 오
     포스트오더 : 왼 -> 오 -> 루 => 가장 최상위 루트가 최종적으로 맨 마지막에 도착
        결국 포스트오더는 서브트리 타고 내려갈때마다(?) 맨 마지막 원소가 해당 서브트리의 루트가 됨

     프리오더 : 루 -> 왼 -> 오 => 루트노드를 우선으로 방문(루 -> 왼쪽 서브트리 이동(루트 우선))

     어렵다 ㅠㅠㅠ 다른 사람 접근법 참고해서 풀었다 https://loosie.tistory.com/345
     분할정복으로 푸는 문제인데,, 이거 관련해서 좀더 공부해봐야곘따
    * */

    // 전위 순회 찾기(루-왼-오)
    static void makePre(int is, int ie, int ps, int pe) {
        // 맨 마지막 친구
        if (ie < is || pe < ps) return;

        int root = postorder[pe];
        int rootIdx = inorderIdx[root];
        int leftSize = rootIdx - is;
        sb.append(root + " ");

        makePre(is, rootIdx - 1, ps, ps + leftSize - 1);
        makePre(rootIdx + 1, ie, ps + leftSize, pe- 1);
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        inorder = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        postorder = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        inorderIdx = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            inorderIdx[inorder[i]] = i;
        }

        makePre(1, N, 1, N);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}