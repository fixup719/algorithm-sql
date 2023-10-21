

import java.io.*;
import java.util.*;


public class Main {
    static int N, M, V;
    static int[][] map;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int node){
        // 첫 노드 방문
        visited[node] = true;

        sb.append(node + " ");

        for(int i=1; i<=N; i++){
            if(!visited[i] && map[node][i] == 1){
                dfs(i);
            }
        }
    }

    static void bfs(int node){
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;

        while(!q.isEmpty()){
            int num = q.poll();
            sb.append(num + " ");

            for(int i=1; i<=N; i++){
                if(map[num][i]==1 && !visited[i]){
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map[from][to] = 1;
            map[to][from] = 1;
        }

        visited = new boolean[N+1];
        dfs(V);

        sb.append("\n");

        visited = new boolean[N+1];
        bfs(V);

        System.out.println(sb);

    }
}