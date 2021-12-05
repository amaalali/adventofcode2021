import bingo._

def run(input: Seq[Int], boards: Iterator[BingoBoard]): Int = {

  def checkValues(
      _boards: Seq[BingoBoard],
      values: Seq[Int]
  ): (Int, BingoBoard) = {
    val currentDraw = values.head
    val updatedBoards = _boards.map(_.markSquare(currentDraw))
    val uncompletedBoards = updatedBoards.filterNot(_.isComplete)

    (
      updatedBoards.filter(_.isComplete).isEmpty,
      uncompletedBoards.isEmpty
    ) match {
      case (true, true)  => throw new java.lang.Exception("FOOOOOOO")
      case (false, true) => (currentDraw, updatedBoards.head)
      case _             => checkValues(uncompletedBoards, values.tail)
    }
  }

  val (losingNumber, losingBoard) = checkValues(boards.toVector, input)

  losingNumber * losingBoard.unmarked.sum
}
