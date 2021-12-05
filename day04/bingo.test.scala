// using lib org.scalatest::scalatest:3.2.10
// using lib org.scalactic::scalactic:3.2.10

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._
import org.scalactic.TypeCheckedTripleEquals._
import org.scalatest.prop.TableDrivenPropertyChecks._

import bingo._

class BingoSpec extends AnyFreeSpec {
  "BingoBoard" - {
    "markSquare" - {
      "a board that has less than 5 checks will not be complete" in {
        val bingoBoard = BingoBoard(
          Seq(14, 21, 17, 24, 4),
          Seq(10, 16, 15, 9, 19),
          Seq(18, 8, 23, 26, 20),
          Seq(22, 11, 13, 6, 5),
          Seq(2, 0, 12, 3, 7)
        )

        val result = bingoBoard
          .markSquare(14)
          .markSquare(21)
          .markSquare(17)
          .markSquare(24)

        result.isComplete must ===(false)
      }

      "a board where a full row has been marked will be complete" in {
        val drawNumbers = Table(
          ("1", "2", "3", "4", "5"),
          (14, 21, 17, 24, 4),
          (10, 16, 15, 9, 19),
          (18, 8, 23, 26, 20),
          (22, 11, 13, 6, 5),
          (2, 0, 12, 3, 7)
        )
        val bingoBoard = BingoBoard(
          Seq(14, 21, 17, 24, 4),
          Seq(10, 16, 15, 9, 19),
          Seq(18, 8, 23, 26, 20),
          Seq(22, 11, 13, 6, 5),
          Seq(2, 0, 12, 3, 7)
        )

        forAll(drawNumbers) { (one, two, three, four, five) =>
          val result = bingoBoard
            .markSquare(one)
            .markSquare(two)
            .markSquare(three)
            .markSquare(four)
            .markSquare(five)

          result.isComplete must ===(true)
        }
      }

      "a board where a full column has been checked will be complete" in {
        val drawNumbers = Table(
          ("1", "2", "3", "4", "5"),
          (14, 10, 18, 22, 2),
          (21, 16, 8, 11, 0),
          (17, 15, 23, 13, 12),
          (24, 9, 26, 6, 3),
          (4, 19, 20, 5, 7)
        )
        val bingoBoard = BingoBoard(
          Seq(14, 21, 17, 24, 4),
          Seq(10, 16, 15, 9, 19),
          Seq(18, 8, 23, 26, 20),
          Seq(22, 11, 13, 6, 5),
          Seq(2, 0, 12, 3, 7)
        )

        forAll(drawNumbers) { (one, two, three, four, five) =>
          val result = bingoBoard
            .markSquare(one)
            .markSquare(two)
            .markSquare(three)
            .markSquare(four)
            .markSquare(five)

          result.isComplete must ===(true)
        }
      }
    }

    "unmarked" - {
      "when no squares have been marked" in {
        val bingoBoard = BingoBoard(
          Seq(14, 21, 17, 24, 4),
          Seq(10, 16, 15, 9, 19),
          Seq(18, 8, 23, 26, 20),
          Seq(22, 11, 13, 6, 5),
          Seq(2, 0, 12, 3, 7)
        )

        bingoBoard.unmarked mustEqual Seq(
          14, 21, 17, 24, 4, 10, 16, 15, 9, 19, 18, 8, 23, 26, 20, 22, 11, 13,
          6, 5, 2, 0, 12, 3, 7
        )
      }

      "when some squares have been marked, then those squares are not returned" in {
        val bingoBoard =
          BingoBoard(
            Seq(14, 21, 17, 24, 4),
            Seq(10, 16, 15, 9, 19),
            Seq(18, 8, 23, 26, 20),
            Seq(22, 11, 13, 6, 5),
            Seq(2, 0, 12, 3, 7)
          )
            .markSquare(14)
            .markSquare(21)
            .markSquare(17)
            .markSquare(24)
            .markSquare(10)
            .markSquare(16)
            .markSquare(15)
            .markSquare(9)
            .markSquare(18)
            .markSquare(8)
            .markSquare(23)
            .markSquare(26)
            .markSquare(22)
            .markSquare(11)
            .markSquare(13)
            .markSquare(6)
            .markSquare(7)

        bingoBoard.unmarked mustEqual Seq(
          4, 19, 20, 5, 2, 0, 12, 3
        )
      }
    }
  }
}
