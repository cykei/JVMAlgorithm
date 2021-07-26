package programmers

class 키패드누르기 {
    // 6시 50분 ~ 7시 52분
    fun solution(numbers: IntArray, hand: String): String {
        var answer = ""
        var left = 10
        var right = 12
        var list = mutableListOf<Pair<Int,Int>>()
        for (i in 0..13){
            if (i-1<0) list.add(Pair((i-1)/3, 0))
            else list.add(Pair((i-1)/3, (i-1)%3))
        }
        list[0] = list[11]

        for ( i in numbers) {
            if( i==1 || i==4 || i== 7) {
                left = i
                answer += "L"
            } else if (i==3 || i==6 || i==9) {
                right = i
                answer += "R"
            } else {
                val lPos : Pair<Int,Int> = list[left]
                val rPos : Pair<Int,Int> = list[right]
                val iPos : Pair<Int,Int> = list[i]
                val distL = kotlin.math.abs(lPos.first - iPos.first) + kotlin.math.abs(lPos.second - iPos.second)
                val distR = kotlin.math.abs(rPos.first - iPos.first) + kotlin.math.abs(rPos.second - iPos.second)
                if (distL < distR) {
                    left = i
                    answer += "L"
                }else if (distL > distR) {
                    right = i
                    answer += "R"
                }else {
                    if( hand.equals("left")) {
                        left = i
                        answer += "L"
                    }else{
                        right = i
                        answer += "R"
                    }
                }

            }
        }
        return answer
    }
}

fun main(){
    val arr = intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5)
    val sol = 키패드누르기()
    val result : String = sol.solution(arr, "right")
    println(result)

}