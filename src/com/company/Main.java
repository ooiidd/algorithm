package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int size = 2005;
    public static void main(String[] args) throws Exception {
        //포드 풀커슨 알고리즘
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Set<Integer>[] adj = new HashSet[size];
        for(int i=0;i<size;i++){
            adj[i]=new HashSet<>();
        }

        int Capacity[][] = new int[size][size];
        for(int i = 1 ; i <= n ; i ++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0;j<cnt;j++){
                int a = Integer.parseInt(st.nextToken());
                Capacity[i][a+1000] = 1;
                Capacity[a+1000][i] = 0;
                adj[i].add(a+1000);
//                adj[a+1000].add(i);
            }
            Capacity[0][i] = 2;
            Capacity[i][0] = 0;
//            adj[i].add(0);
            adj[0].add(i);
        }
        for(int i=1 ; i <= m; i++){
            Capacity[2001][i+1000]=0;
            Capacity[i+1000][2001]=1;
            adj[i+1000].add(2001);
//            adj[2001].add(i+1000);
        }

        // end of input
        System.out.println(networkflow(0,2001,Capacity,adj));
    }

    private static int networkflow(int start, int end, int[][] capacity, Set<Integer>[] adj) {

        int size = capacity.length;
        // a->b에서 정방향이면 b->a의 역방향의 -유량을 표시해준다.
        int flow[][] = new int[size][size];

        Queue<Integer> dq = new LinkedList<>();
        int res = 0;
        while(true) {
            dq.clear();
            int parent[] = new int[size];
            for(int i = 0 ; i < size ; i++) {parent[i] = -1;}
            dq.add(start); parent[start] = start;
            while(!dq.isEmpty() && parent[end] == -1) {
                int now = dq.poll();
                Iterator<Integer> iter = adj[now].iterator();
//                for(int i=0;i<size;i++) {
                while(iter.hasNext()){
                    int i = iter.next();
                    if(capacity[now][i]-flow[now][i]>0 && parent[i]== -1) {
                        // 연결되어서 유량을 보낼수 있는 경우
                        // 경로가 결정되지 않았을 경우
                        dq.add(i);
                        parent[i] = now;
                    }
                }
            }


            if(parent[end]==-1) {
                break;
                // 유량경로가없으면 종료
            }

            // 한스텝에 얼마의 유량을 흘릴것인지?
            int flowamount = 2147000000;

            for(int s = end ; s != start ; s = parent[s]) {
                // 거꾸로 올라가면서 유량의 최솟값을 찾음
                flowamount = Math.min(flowamount, capacity[parent[s]][s] - flow[parent[s]][s]);
            }

            for(int s = end ; s!= start ; s = parent[s]) {
                // 부모 -> 자식으로 flowamount가 흐르면 자식->부모로 -flowamount가 흐른다.
                flow[parent[s]][s] +=flowamount;
                flow[s][parent[s]] -=flowamount;
            }
            res+=flowamount;

        }
        return res;

    }
}