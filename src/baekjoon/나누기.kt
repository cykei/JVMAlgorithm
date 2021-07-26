package baekjoon

import java.util.*

/*
7월 16일 오후 11시 42분 ~ 7월 17일 오후 5시 26분 -> 모르겠음. 여전히 못풀겠음.
https://www.acmicpc.net/problem/21757
 */
fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val n = nextInt()
    val arr = Array(n) {nextInt()}

    // 각 인덱스까지 합한 결과를 구해놓는다.
    val sum = Array(n+1) {0}
    for (i in 1..n) {
        sum[i] = sum[i-1] + arr[i-1]
    }

    // 4로 나누어 떨어지지 않는다면 경우의 수는 0이다.
    if (sum[n] % 4 !=0) println(0)
    else {
        val sectionSum = sum[n] / 4
        var dp = Array(n+1){Array(n+1){0}}
        for ( i in 1..n ){
            dp[i][0] = 1
            for ( j in 1..3) {
                dp[i][j] = dp[i - 1][j]
                if (sectionSum * j == sum[i]) dp[i][j] += dp[i - 1][j - 1]
            }
        }
        println(dp[n-1][3])
    }
}