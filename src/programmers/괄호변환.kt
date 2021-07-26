package programmers

/*
2021-07-06 11:37 ~ 12:42
programmers - 괄호변환
https://programmers.co.kr/learn/courses/30/lessons/60058/solution_groups?language=kotlin&type=all
[문제]
균형잡힌 괄호문자열을 올바른 괄호문자열로 바꿔라
[예제]
"()))((()"  ->	"()(())()"
*/

class 괄호변환 {
    fun solution(p: String): String {
        var answer = ""
        answer = split(p)
        return answer
    }
    fun split(p: String): String {
        var u = ""
        var v = ""
        var check = 0
        var count = 0

        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if( p.isEmpty()) return ""

        for( i in p ) {
            // 2-1. 분리할 수 없는 "균형잡힌 괄호문자열"을 찾습니다. p자체가 "균형잡힌 괄호문자열" 이기 때문에 못찾는 경우는 없습니다.
            if (i == '(') check++
            else if (i == ')') check--
            count++
            if (check == 0) {
                // 2-2. (, ) 의 쌍이 맞아지는 순간이 최초의 분리할 수 없는 "균형잡힌 괄호문자열"을 찾는 순간입니다.
                u = p.substring( 0, count)
                v = p.substring(count, p.length)
                break
            }
        }

        // 3. 문자열 u가 "올바른 괄호문자열" 이라면 문자열 v에 대해 1단계부터 재실시한 후 u에 붙여서 반환합니다.
        if( checkCorrect(u) ) return u + split(v)
        else {
            // 4. 문자열 u가 "올바른 괄호문자열" 이 아니라면 아래 과정을 수행합니다.
            // 4-1 ~ 4-3. 빈문자열에 첫번째 문자로 '('를 붙입니다. v에 대해 1단계부터 재귀적으로 수행한 결과를 붙입니다. ')'를 다시 붙입니다.
            var a = "(" + split(v) +")"

            // 4-4. u의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            var b = u.substring(1, u.length-1)
            for (t in b){
                if (t == ')') a += "("
                else a += ")"
            }
            return a
        }
    }

    // 방법 1 (개천재 방법)
    // i 가 처음에 ')' 가 나와버리면 두번째 if 에 걸려서 check가 음수가 되니까 false 가 반환된다.
    // 즉, 올바른 문자열이라 함은 항상 ( 가 먼저 나올것이기 때문에 check가 음수가 되는 순간 올바른 문자열이 아니게 된다.
    fun checkCorrect(s: String): Boolean {
        var check = 0
        for( i in s) {
            if(i == '(') check++
            if(i == ')') check--
            if(check < 0) return false
        }
        return true
    }

    // 방법 2 (평범하게 stack 이용)
    /*
    fun checkCorrect(s: String): Boolean {
        val stack = ArrayList<Char>()
        for (c in s){
            if (c=='(') {
                stack.add(c)
            } else if (c==')') {
                if (stack.isEmpty()) return false
                else {
                    if (stack.last() == '(') stack.removeAt(stack.lastIndex)
                    else stack.add(c)
                }
            }
        }
        return stack.isEmpty()
    }

     */
}


fun main(){
    val sol = 괄호변환()
    val result : String = sol.solution(")(")
    println(result)

}

