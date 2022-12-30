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

enum class XYZMode {
    Shape,
    Outcome
}

class RockPaperScissors(_rawInput: List<String>) {
    private val opponentShapeMap = mapOf('A' to ScoreShape.ROCK, 'B' to ScoreShape.PAPER, 'C' to ScoreShape.SCISSORS)
    private val elfShapeMap = mapOf('X' to ScoreShape.ROCK, 'Y' to ScoreShape.PAPER, 'Z' to ScoreShape.SCISSORS)

    private val elfCombatMap = mapOf('X' to ScoreCombat.LOST, 'Y' to ScoreCombat.DRAW, 'Z' to ScoreCombat.WON)

    private val rawInput = _rawInput

    fun calTotalScoreOfAllRounds(mode: XYZMode): Int {
        var totalScore = 0
        val calcMethod = if (mode == XYZMode.Shape) {
            ::calcScoreForRoundIfXYZAsShape
        } else {
            ::calcScoreForRoundIfXYZAsOutcome
        }
        for (i in rawInput) {
            totalScore += calcMethod(i[0], i[2])
        }
        return totalScore
    }

    private fun calcScoreForRoundIfXYZAsShape(opponent: Char, elf: Char): Int {
        return calcScoreForRound(
            opponentShapeMap.getOrDefault(opponent, ScoreShape.ROCK),
            elfShapeMap.getOrDefault(elf, ScoreShape.ROCK)
        )
    }

    private fun calcScoreForRoundIfXYZAsOutcome(opponent: Char, elfResult: Char): Int {
        if (elfCombatMap[elfResult] == ScoreCombat.DRAW) {
            return calcScoreForRound(
                opponentShapeMap.getOrDefault(opponent, ScoreShape.ROCK),
                opponentShapeMap.getOrDefault(opponent, ScoreShape.ROCK)
            )
        } else if (elfCombatMap[elfResult] == ScoreCombat.LOST) {
            if (opponentShapeMap[opponent] == ScoreShape.ROCK) {
                return calcScoreForRound(
                    ScoreShape.ROCK,
                    ScoreShape.SCISSORS
                )
            } else if (opponentShapeMap[opponent] == ScoreShape.PAPER) {
                return calcScoreForRound(
                    ScoreShape.PAPER,
                    ScoreShape.ROCK
                )
            } else {
                return calcScoreForRound(
                    ScoreShape.SCISSORS,
                    ScoreShape.PAPER
                )
            }
        } else {
            if (opponentShapeMap[opponent] == ScoreShape.ROCK) {
                return calcScoreForRound(
                    ScoreShape.ROCK,
                    ScoreShape.PAPER
                )
            } else if (opponentShapeMap[opponent] == ScoreShape.PAPER) {
                return calcScoreForRound(
                    ScoreShape.PAPER,
                    ScoreShape.SCISSORS
                )
            } else {
                return calcScoreForRound(
                    ScoreShape.SCISSORS,
                    ScoreShape.ROCK
                )
            }
        }
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