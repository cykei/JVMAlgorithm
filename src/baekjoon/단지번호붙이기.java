package baekjoon;


import java.awt.Point;
import java.util.*;
//https://www.acmicpc.net/problem/2667
public class 단지번호붙이기 {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int map[][];
    static int cnt;
    static int n;
    static ArrayList<Integer> answer = new ArrayList<>();
    static void bfs(int i, int j) {
        cnt++;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i,j));
        map[i][j] = cnt;
        int local=1;
        while(!q.isEmpty()){
            Point cur = q.poll();
            for (int d=0; d<4; d++) {
                int nx = cur.x+dx[d];
                int ny = cur.y+dy[d];
                if(nx < 0 || ny <0 || nx >= n || ny >=n ) continue;
                if(map[nx][ny]==1) {
                    q.add(new Point(nx, ny));
                    map[nx][ny] = cnt; //중요!! nx,ny 라고 안하고 x,y라고 써서 계속 틀렸음!
                    local++;
                }
            }
//            System.out.println();
//            for (int k=0; k<n; k++) {
//                for (int p=0; p<n; p++) {
//                    System.out.print(map[k][p]+" ");
//                }
//                System.out.println();
//            }
//            if(local>7) break;
        }
        answer.add(local);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        map = new int[n][n];
        cnt=1;

        for (int i=0; i<n; i++){
            String s = scanner.next();
            for (int j=0; j<n; j++) {
                map[i][j] = (s.charAt(j) == '0') ? 0 : 1;
            }
        }
        for (int i=0; i<n ;i++) {
            for (int j=0; j<n; j++) {
                if(map[i][j]==1) {
                    bfs(i,j);
                }
            }
        }
        System.out.println(cnt-1);
        Collections.sort(answer);
        answer.forEach(System.out::println);
    }
}
