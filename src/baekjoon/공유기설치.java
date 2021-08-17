package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/*
2021-08-17
백준 2110번 https://www.acmicpc.net/problem/2110

문제 : 가장 인접한 두 공유기의 간격이 최대가 되게하라.
풀이 : 거리 기준 이분 탐색
 */
public class 공유기설치 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int c = scan.nextInt();
        int[] array = new int[n];
        for (int i=0; i<n; i++) {
            array[i] = scan.nextInt();
        }

        Arrays.sort(array);

        int left = 1; // 최소거리
        int right = array[n-1] - array[0]; // 최대거리
        int ans = 0;
        while(left <= right) {
            int mid = (left + right)/2;
            int start = array[0];
            int cnt = 1;
            for (int i=1; i<n; i++) {
                int d = array[i] - start;
                if (mid <= d) {
                    cnt++;
                    start = array[i];
                }
            }

            if (cnt >= c) {
                ans = mid;
                left = mid+1;
            } else {
                // 간격을 좁힌다.
                right = mid-1;
            }
        }
        System.out.println(ans);
    }
}
