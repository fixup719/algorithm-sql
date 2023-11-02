

import java.io.*;
import java.util.*;


public class Main {
    static int N, M, V;
    static int[][] map;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int v){
        visited[v] = true;

        sb.append(v + " ");

        for(int i=1; i<=N; i++){
            if(!visited[i] && map[v][i] == 1){
                visited[i] = true;
                dfs(i);
            }
        }
    }

    static void bfs(int v){
        visited[v] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);

        while(!q.isEmpty()){
            int node = q.poll();

            sb.append(node + " ");

            for(int i=1; i<=N; i++){
                if(!visited[i] && map[node][i]==1){
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
        while(M --> 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            map[node1][node2]=1;
            map[node2][node1]=1;
        }

        visited = new boolean[N+1];
        dfs(V);

        sb.append("\n");

        visited = new boolean[N+1];
        bfs(V);

        System.out.println(sb);
    }
}