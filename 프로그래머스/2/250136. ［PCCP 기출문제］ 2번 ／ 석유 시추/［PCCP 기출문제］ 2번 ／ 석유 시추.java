import java.util.*;
import java.io.*;
/*
    [문제 요약]
    세로길이 N, 가로길이 M인 격자 모양 땅 속에 석유 발견
    석유는 여러 덩어리로 나뉘어 묻힘
    가장 많은 석유를 뽑을 수 있는 시추관 위치를 찾으려 한다
    시추관은 특정 열을 관통하는 형태이다.
    이때, 시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량을 RETURN
    
    [입력]
    2차원 배열 형태로 땅 정보가 주어짐 (0 : 빈 땅, 1 : 석유) : 1 <= N, M <= 500

*/

class Solution {
    static int n, m;
    static int[] parent;
    static int[] size;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static boolean[][] visited;
    
    static int find (int x) {
        if (parent[x] == x) return parent[x];
        
        parent[x] = find(parent[x]);
        return parent[x];
    }
    
    static void union (int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a == b) return;
        
        if (size[a] >= size[b]) {
            parent[b] = a;
            size[a] += size[b];
        } else {
            parent[a] = parent[b];
            size[b] += size[a];
        }
    }
    
    static void dfs(int row, int col, int[][] land) {
        visited[row][col] = true;
        int mrow, mcol, toNum;
        for (int dir = 0; dir < 4; dir++) {
            mrow  = row + delR[dir];
            mcol = col + delC[dir];
            
            if (mrow < 0 || mcol < 0 || n <= mrow || m <= mcol 
                    || land[mrow][mcol] == 0 || visited[mrow][mcol]) continue;

            union(row * m + col, mrow * m + mcol);
            dfs(mrow, mcol, land);
        }
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        
        n = land.length; // 세로 길이
        m = land[0].length;  // 가로 길이
        parent = new int[n * m + 1];
        for (int i = 0; i < n * m + 1; i++) {
            parent[i] = i;
        }
        size = new int[n * m + 1];
        Arrays.fill(size, 1);
        visited = new boolean[n][m];
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (visited[row][col] || land[row][col] == 0) continue;
                
                dfs(row, col, land);
            }
        }
        
        Set<Integer> set = new TreeSet<>();
        int sum = 0;
        for (int col = 0; col < m; col++) {
            for (int row = n - 1; row >= 0; row--) {
                if (land[row][col] == 1) {
                    set.add(find(row * m + col));
                }
            }
            
            for (Integer el : set) {
                sum += size[el];
            }
            
            answer = Math.max(answer, sum);
                
            sum = 0;
            set.clear();
        }
        
        return answer;
    }
}