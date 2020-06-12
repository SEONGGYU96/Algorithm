class Solution {
    fun solution(answers: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        val typeAnswers = Array(3) { IntArray(answers.size) }
        val answerCount = intArrayOf(0, 0, 0)

        typeAnswers[0] = generateAnswers(1, answers.size)
        typeAnswers[1] = generateAnswers(2, answers.size)
        typeAnswers[2] = generateAnswers(3, answers.size)
        for (i in answers.indices) {
            for (j in typeAnswers.indices) {
                if (answers[i] == typeAnswers[j][i]) {
                    answerCount[j]++
                }
            }
        }
        var max = 0;
        for (i in answerCount.indices) {
            if (answerCount[i] > max) {
                max = answerCount[i]

            }
        }

        for (i in answerCount.indices) {
            if (answerCount[i] == max) {
                answer.add(i + 1)
            }
        }

        return answer.toIntArray()
    }

    private fun generateAnswers(type: Int, numberOfAnswer: Int): IntArray {
        val answers = IntArray(numberOfAnswer)
        var increase = 0
        var time = 0
        var j = 2
        for (i in 0 until numberOfAnswer) {
            answers[i] = when (type) {
                1 -> {
                    i % 5 + 1
                }
                2 -> if ((i % 2) == 0) {
                    2
                } else {
                    var temp = ++increase

                    if (increase == 2) {
                        temp = ++increase

                    } else if (increase > 5) {
                        increase /= 5 + 1
                        temp = increase
                    }
                    temp
                }
                3 -> {
                    val temp = (j % 5) + 1
                    if (time < 1) {
                        time++
                    } else {
                        j++
                        time = 0
                    }
                    temp
                }
                else -> -1
            }
        }
        return answers
    }
}
