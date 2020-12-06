import java.util.*;

public class BipartMatching {
    public static int[][] c;
    public static int[][] f;
    public static int[] match;
    public static List<Integer>[] adj;
    public static boolean[] visit;
    public static boolean dfs(int a){
        if(visit[a])
            return false;
        visit[a] = true;
        for(int b : adj[a]){
            if(match[b]==-1 || dfs(match[b])) {
                match[b] = a;
                return true;
            }
        }
        return false;
    }
    public static int bipartMatching(int N) {
        int ret = 0;
        Arrays.fill(match,-1);
        for(int i=1;i<=N;i++){
            Arrays.fill(visit,false);
            if(dfs(i)) ++ ret;
        }
        return ret;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N=scan.nextInt();
        int M = scan.nextInt();

        c = new int[N+1][M+1];
        f = new int[N+1][M+1];
        match = new int[201];
        visit = new boolean[201];

        adj = new ArrayList[201];
        for(int i=0;i<201;i++)
            adj[i] = new ArrayList<>();

        for(int i=1;i<=N;i++){
            int count = scan.nextInt();
            for(int j=0;j<count;j++){
                int want = scan.nextInt();
//                c[i][want] = 1;
                adj[i].add(want);
//                adj[want].add(i);
            }
//            adj[0].add(i);
//            adj[i].add(0);
//            c[0][i]=count;
        }
        System.out.println(bipartMatching(N));
//        for(int i=1;i<=M;i++){
//            adj[i+200].add(401);
//            adj[401].add(i+200);
//        }




    }
}
