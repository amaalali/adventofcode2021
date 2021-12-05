import bingo._

def run(input: Seq[Int], boards: Iterator[BingoBoard]): Int = {

  def checkValues(
      _boards: Seq[BingoBoard],
      values: Seq[Int]
  ): (Int, BingoBoard) = {
    val currentDraw = values.head
    val updatedBoards = _boards.map(_.markSquare(currentDraw))

    updatedBoards.find(_.isComplete) match {
      case Some(completed) => (currentDraw, completed)
      case None            => checkValues(updatedBoards, values.tail)
    }
  }

  val (winningNumber, winningBoard) = checkValues(boards.toVector, input)

  winningNumber * winningBoard.unmarked.sum
}
