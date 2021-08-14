package programmers

/*
2021-07-26 10:03 ~ 10:49
https://programmers.co.kr/learn/courses/30/lessons/77884
문제
left부터 right까지의 모든 수들 중에서, 약수의 개수가 짝수인 수는 더하고, 약수의 개수가 홀수인 수는 뺀 수를 return 하도록 solution 함수를 완성해주세요.
예제
13	17	43
풀이
완전탐색. 1부터 1000까지 돌면서 곱하기를 하면서 +1 씩 한다. 왜냐면 곱해졌다는 것은 해당 숫자가 곱해진 수에 한번씩 들어가 있다는 이야기이기 때문
(하.. 이 쉬운걸 하는데 이렇게 오래걸리다니..ㅠㅠ)
*/
class 약수의갯수와덧셈 {
    fun solution(left: Int, right: Int): Int {
        var answer: Int = 0
        var list = Array<Int>(1001){0}
        for ( i in 1..1000){
            for (j in 1..1000) {
                var cur = i * j
                if(cur > 1000) break
                list[cur]++
            }
        }

        for (i in left..right){
            if ( list[i]%2==0 ) answer +=i
            else answer -=i
        }
        return answer
    }
}

class Solution {
    fun solution(left: Int, right: Int): Int {
        var answer: Int = 0

        for(i in left .. right) {
            var count = 0

            for(j in 1 .. i) {
                if(i % j == 0) count++
            }
            if(count % 2 == 0) answer += i
            else answer -= i

        }

        return answer
    }
}

fun main(){
    var instance = 약수의갯수와덧셈()
    var result = instance.solution(13, 17)
    println(result)
}