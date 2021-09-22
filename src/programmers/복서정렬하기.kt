package programmers

import kotlin.collections.ArrayList

/*
2021-09-20
https://programmers.co.kr/learn/courses/30/lessons/85002
*/
data class Player(val id:Int, val per:Float, val cnt:Int, val weight:Int ): Comparable<Player>{
    override fun compareTo(other: Player): Int {
        return when {
            per != other.per -> if (other.per > per) 1 else -1
            cnt != other.cnt -> other.cnt - cnt
            weight != other.weight -> other.weight - weight
            else -> id - other.id
        }
    }
}
class 복서정렬하기 {
    fun solution(weights: IntArray, head2head: Array<String>): IntArray {
       // var answer = IntArray(weights.size)
        var persons: ArrayList<Player> = arrayListOf()
        for (i in 0..weights.size-1) {
            val score = head2head[i];
            var total =0
            var win = 0
            var cnt = 0
            for (j in 0..score.length-1){
                when(score[j]) {
                    'L' -> {
                        total++
                    }
                    'W' -> {
                        total++
                        win++
                        if(weights[j] > weights[i]) cnt++
                    }
                }
            }
            var per: Float
            if (total == 0) per = 0.0f
            else per = (win / total.toFloat())

            val player = Player(i, per, cnt, weights[i])
            persons.add(player)
        }
        val answer = persons.sorted().map{it.id + 1}.toIntArray()
//        persons.sortWith(compareBy({it.per}, {it.cnt}, {it.weight}, {it.id}))
//        var idx=0
//        for (player in persons){
//            answer[idx] = player.id+1
//            idx++
//        }
        return answer
    }
}


fun main(){
    var instance = 복서정렬하기()
    var a = intArrayOf(60,70,60)
    var b = arrayOf("NNN","NNN","NNN")
    var result = instance.solution(a, b)
    println(result)
}