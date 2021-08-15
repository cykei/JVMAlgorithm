package baekjoon

import java.util.*

/*
2887 행성터널
https://www.acmicpc.net/problem/2887
2021-08-14 10시~
[문제]
 행성을 연결하는데 도로 만드는 비용 = min( |Xa-Xb|, |Ya-Yb|, |Za-Zb|)
 행성이 n개, 도로는 n-1개 만들려고 한다.
 3차원 속 행성들을 모두 연결하는데 드는 최소비용은?

[풀이]
 유형 : 크루스칼 알고리즘 (union - find)
 간선의 가중치 를 정하는것이 중요하다.
 x, y, z 별로 정렬해서 최소값을 갖는 간선을 추려낸다.
 그렇게 추려낸 간선들을 또 정렬해서 최소값을 갖는 경로를 찾는다.
 참고 :
 https://chanhuiseok.github.io/posts/baek-34/  <-틀린코드
 https://dev-jk.tistory.com/29
 */
var parent = IntArray(10001)
fun find(a:Int):Int {
    var x:Int = a
    if(parent[x] == x) return x
    while(parent[x] != x) x = parent[x]
    return x
}
fun union(a:Int, b:Int) {
    val rootA = find(a)
    val rootB = find(b)

    if(rootA != rootB) parent[rootA] = rootB
}
data class Point(val weight:Int,val start:Int,val end:Int)
fun main(args: Array<String>): Unit = with(Scanner(System.`in`)) {

    val n = nextInt()
    var pointX = arrayListOf<Pair<Int, Int>>()
    var pointY = arrayListOf<Pair<Int, Int>>()
    var pointZ = arrayListOf<Pair<Int, Int>>()

    for (i in 0..n-1){
        val a = nextInt();
        val b = nextInt();
        val c = nextInt();
        pointX.add(Pair(a,i));
        pointY.add(Pair(b,i));
        pointZ.add(Pair(c,i));
    }

    for (i in 0..n-1) {
        parent[i] = i
    }
//    val comparator = kotlin.Comparator { o1:Pair<Int,Int>, o2:Pair<Int,Int> ->
//        var result : Pair<Int,Int> = o2
//        if (o1.first < o2.first) result = o1
//        return@Comparator result.first
//        //return@Comparator (o1.first < o2.first)? o1.first
//    }

    var sortedX = pointX.sortedBy { it.first }
    var sortedY = pointY.sortedBy { it.first }
    var sortedZ = pointZ.sortedBy { it.first }

    var points = arrayListOf<Point>()
    for (i in 0..n-2) {
        points.add(Point(sortedX[i+1].first - sortedX[i].first, sortedX[i].second, sortedX[i+1].second))
        points.add(Point(sortedY[i+1].first - sortedY[i].first, sortedY[i].second, sortedY[i+1].second))
        points.add(Point(sortedZ[i+1].first - sortedZ[i].first, sortedZ[i].second, sortedZ[i+1].second))

    }

    //크루스칼 알고리즘 (사이클을 만들면 안되기 때문)
    var sortedPoint = points.sortedBy { it.weight }
    var total = 0

    for (sp in sortedPoint) {
        val weight = sp.weight
        val start = sp.start
        val end = sp.end

        if( find(start) != find(end)) {
            union(start, end)
            total += weight
        }

    }

    println(total)
}
