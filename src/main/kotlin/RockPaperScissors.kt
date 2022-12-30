enum class ScoreCombat(val outcome: Int) {
    LOST(0),
    DRAW(3),
    WON(6)
}

enum class ScoreShape(val shapeValue: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

class RockPaperScissors(_rawInput: List<String>) {
    private val opponentShapeMap = mapOf('A' to ScoreShape.ROCK, 'B' to ScoreShape.PAPER, 'C' to ScoreShape.SCISSORS)
    private val elfShapeMap = mapOf('X' to ScoreShape.ROCK, 'Y' to ScoreShape.PAPER, 'Z' to ScoreShape.SCISSORS)
    private val rawInput = _rawInput

    fun calTotalScoreOfAllRounds(): Int {
        var totalScore = 0
        for (i in rawInput) {
            totalScore += calcScoreForRound(i[0], i[2])
        }
        return totalScore
    }

    private fun calcScoreForRound(opponent: Char, elf: Char): Int {
        return calcScoreForRound(
            opponentShapeMap.getOrDefault(opponent, ScoreShape.ROCK),
            elfShapeMap.getOrDefault(elf, ScoreShape.ROCK)
        )
    }

    private fun calcScoreForRound(opponent: ScoreShape, elf: ScoreShape): Int {
        var combatResult = 0;

        if (opponent == elf) {
            combatResult = ScoreCombat.DRAW.outcome
        } else {
            if (opponent == ScoreShape.ROCK) {
                if (elf == ScoreShape.PAPER) {
                    combatResult = ScoreCombat.WON.outcome
                }
                if (elf == ScoreShape.SCISSORS) {
                    combatResult = ScoreCombat.LOST.outcome
                }
            } else if (opponent == ScoreShape.PAPER) {
                if (elf == ScoreShape.ROCK) {
                    combatResult = ScoreCombat.LOST.outcome
                }
                if (elf == ScoreShape.SCISSORS) {
                    combatResult = ScoreCombat.WON.outcome
                }
            } else if (opponent == ScoreShape.SCISSORS) {
                if (elf == ScoreShape.PAPER) {
                    combatResult = ScoreCombat.LOST.outcome
                }
                if (elf == ScoreShape.ROCK) {
                    combatResult = ScoreCombat.WON.outcome
                }
            }
        }
        return combatResult + elf.shapeValue
    }
}