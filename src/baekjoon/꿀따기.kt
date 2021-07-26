package baekjoon

import java.lang.Integer.max
import java.util.*

/*
2021-07-16 오후 12:43~ 오후 11:25
백준 21758번
https://www.acmicpc.net/problem/21758

[문제]
벌들은 벑통을 향해 일직선으로 날아가면서 벌이 놓였던 장소외의 모든 곳에서 꿀을 딴다.
모을 수 있는 최대의 꿀의 양은?

[풀이]
왼쪽 끝에 벌통을 놓았을 경우 벌1은 오른쪽끝에있어야 꿀이 최대가 된다. 벌2 위치만 합이 최대가 되게 정해주면 됨.
오른쪽 끝에 벌통을 놓았을 경우 벌1은 왼쪽끝.
벌통이 중간에 있을때는 벌들이 양극단에 있을때까 최대다.
3가지 경우를 구해보고 최대값을 도출해낸다.
N이 10만이니까 미리 각 인덱스까지 양방향으로 더한 값을 구해놓은 후에 계산을 진행한다.
 */

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val n = nextInt()
    val arr = Array(n) {nextInt()}

    var temp = 0
    var leftSum = Array(n, {0})
    var rightSum = Array(n, {0})
    for (i in 0..n-1) {
        temp += arr[i]
        leftSum[i] = temp
    }
    temp = 0
    for (i in n-1 downTo (0)) {
        temp += arr[i]
        rightSum[i] = temp
    }

    // 1. 중간에 벌통
    var mid = leftSum[n-1] - arr[0] - arr[n-1]
    temp = 0
    // 1-1. arr[1] 에서 arr[n-2] 사이 최대값을 골라서 벌통을 놓는다.
    for ( i in 1..n-2) {
        temp = max(temp, arr[i])
    }
    mid += temp

    // 2. 오른쪽 끝에 벌통
    var left = leftSum[n-1] - arr[0]
    temp = 0
    // 벌통이랑 벌을 같이 둘수는 없으니까 두번째 벌이 위치할 수 있는 곳은 1부터 n-2 까지이다.
    for (i in 1..n-2) {
        // 2-1. 두번째 벌이 모을 꿀의 양 - 두번째 벌이 놓이 곳의 꿀의 양
        var second = leftSum[n-1] - leftSum[i] - arr[i]
        temp = max(temp, second)
    }
    left = left + temp

    // 3. 왼쪽 끝에 벌통
    var right = rightSum[0] - arr[n-1]
    temp = 0
    for (i in n-2 downTo (1)) {
        var second = rightSum[0] - rightSum[i] - arr[i]
        temp = max(temp, second)
    }
    right = right + temp

    var result = max(max(left, right),mid)
    println(result)
}