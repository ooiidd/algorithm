package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class MaxFlow {
    public static int chartoInt(char a){
        if(a <= 'Z') return a-'A';
        return a-'a'+26;
    }
    public static void main(String[] args) throws Exception{
        //초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
//        int N = scan.nextInt();
        int[][] c = new int[53][53];
        int[][] f = new int[53][53];
        ArrayList<Integer>[] adj = new ArrayList[53];
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0;i<53;i++){
            adj[i]=new ArrayList<Integer>();
        }
        int a, b;
        int u, v, w;

        //Input
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = (int) (st.nextToken().charAt(0));
            b = (int) (st.nextToken().charAt(0));
            if(a <= 'Z') {
                u = a - (int)'A';
            } else {
                u = a - (int)'a' + 26;
            }
            if(b <= 'Z') {
                v = b - (int)'A';
            } else {
                v = b - (int)'a' + 26;
            }
            w = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
            c[u][v] += w;
            c[v][u] += w;
        }
//        while(N-- > 0){
//            String a = scan.next();
//            String b = scan.next();
//            int from=chartoInt(a.charAt(0));
//            int to = chartoInt(b.charAt(0));
//            int tmp = scan.nextInt();
//            c[to][from] = c[from][to] += tmp;
//            adj[from].add(to);
//            adj[to].add(from);
//        }

        int S=0,E=25,total=0;
        int prev[] = new int[53];
        int cur;
        int next;
        int flow = 0;
        while(true){
            Arrays.fill(prev,-1);
            queue.clear();
            queue.add(S);

            cur = 0;
            next = 0;


            while(!queue.isEmpty()){
                cur = queue.poll();
                for(int i = 0 ; i < adj[cur].size();i++){
                    next = adj[cur].get(i);
                    if(c[cur][next]-f[cur][next] > 0 && prev[next] == -1){
                        queue.add(next);
                        prev[next] = cur;
                        if(next == E) break;
                    }
                }
            }
            if(prev[E]==-1) break;

            flow = Integer.MAX_VALUE;

            for(int i=E;i!=S;i=prev[i]){
                flow = Math.min(flow, c[prev[i]][i]-f[prev[i]][i]);
            }
            for(int i=E;i!=S;i=prev[i]){
                f[prev[i]][i] += flow;
                f[i][prev[i]] -= flow;
            }
            total+=flow;
        }
        System.out.println(total);
    }
}
