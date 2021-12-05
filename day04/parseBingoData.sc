import scala.io.BufferedSource
import bingo._

import scala.io.Source

def parseBingoRow(rowString: String): Seq[Int] =
  rowString
    .replace("  ", " ")
    .split(" ")
    .filterNot(_.isEmpty)
    .map(_.toInt)

def run(source: Source): (Seq[Int], Iterator[BingoBoard]) = {
  val lines = source.getLines

  val draws =
    lines
      .take(1)
      .next
      .split(",")
      .map(_.toString.toInt)

  val boards = lines
    .sliding(6, 6)
    .map(_.drop(1).map(parseBingoRow))
    .map(rows => BingoBoard(rows(0), rows(1), rows(2), rows(3), rows(4)))

  (draws, boards)
}
