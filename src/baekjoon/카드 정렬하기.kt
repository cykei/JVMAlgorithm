package baekjoon

import java.util.*

/*
1715 카드 정렬하기
https://www.acmicpc.net/problem/1715
2021-07-17 5시 32분 ~ 6시 15분

우선순위큐를 이용해서 해결. 더한 값을 다시 큐에 넣는다.
NullPointerError 가 나기 쉽기 때문에 (큐에 아무것도 없는데 빼려고 할 수 있음) 주의
 */

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val n = nextInt()
    var arr = Array(n) { nextInt() }

    var pq = PriorityQueue<Int>()
    for (i in 0..n-1) {
        pq.add(arr[i])
    }

    var result = 0
    while(pq.size > 1) {
        var a = pq.peek()
        pq.poll()
        var b = pq.peek()
        pq.poll()
        result += a+b
        if( pq.isNotEmpty()) pq.add(a+b)
    }
    println(result)

}

