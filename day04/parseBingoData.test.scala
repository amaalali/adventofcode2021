// using lib org.scalatest::scalatest:3.2.10
// using lib org.scalactic::scalactic:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class ParseBingoDataSpec extends AnyFreeSpec {

  val data =
    """1,2,3,4,5,61
    |
    |14 21 17 24  4
    |10 16 15  9 19
    |18  8 23 26 20
    |22 11 13  6  5
    | 2  0 12  3  7""".stripMargin

  "parseBingoRow" - {
    "when there are only double digit numbers" in {
      parseBingoData
        .parseBingoRow("14 21 17 24  11") mustEqual List(14, 21, 17, 24, 11)
    }

    "when the first number is a single digit" in {
      parseBingoData
        .parseBingoRow(" 4 21 17 24  11") mustEqual List(4, 21, 17, 24, 11)
    }

    "when the all numbers are single digit" in {
      parseBingoData
        .parseBingoRow(" 4  1  7  4   1") mustEqual List(4, 1, 7, 4, 1)
    }
  }

  "run" - {

    "parses the numbers drawn" in {
      val dataInputStream = Source.fromString(data)

      parseBingoData.run(dataInputStream)._1.toList mustEqual List(
        1, 2, 3, 4, 5, 61
      )
    }

    "parses the bingo boards" - {
      "a board where a full row has been checked will be complete" in {
        val dataInputStream = Source.fromString(data)

        val drawNumbers = Table(
          ("1", "2", "3", "4", "5"),
          (14, 21, 17, 24, 4),
          (10, 16, 15, 9, 19),
          (18, 8, 23, 26, 20),
          (22, 11, 13, 6, 5),
          (2, 0, 12, 3, 7)
        )

        val bingoBoard = parseBingoData.run(dataInputStream)._2.next

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
        val dataInputStream = Source.fromString(data)

        val drawNumbers = Table(
          ("1", "2", "3", "4", "5"),
          (14, 10, 18, 22, 2),
          (21, 16, 8, 11, 0),
          (17, 15, 23, 13, 12),
          (24, 9, 26, 6, 3),
          (4, 19, 20, 5, 7)
        )

        val bingoBoard = parseBingoData.run(dataInputStream)._2.next

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
  }
}
