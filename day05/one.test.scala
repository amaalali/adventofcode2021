// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class OneSpec extends AnyFreeSpec {
  "run" - {
    "when there is 1 line, should return 0" in {
      val data = Seq(
        ((9, 7), (7, 7))
      )
      one.run(data) mustEqual 0
    }

    "when there are multiple lines" - {
      "when lines don't intersect" in {
        val line1 = ((9, 7), (7, 7))
        val line2 = ((9, 6), (7, 6))

        val data = Seq(
          line1,
          line2
        )

        one.run(data) mustEqual 0
      }

      "when two of the lines intersect" in {
        val verticalLine = ((1, 2), (1, 10))
        val horizontalLine = ((0, 3), (10, 3))

        val data = Seq(
          verticalLine,
          horizontalLine
        )

        one.run(
          Seq(
            verticalLine,
            horizontalLine
          )
        ) mustEqual 1

        one.run(
          Seq(
            horizontalLine,
            verticalLine
          )
        ) mustEqual 1
      }

      "when there are multiple lines intersecting" in {
        val data = Seq(
          ((0, 9), (5, 9)),
          ((8, 0), (0, 8)),
          ((9, 4), (3, 4)),
          ((2, 2), (2, 1)),
          ((7, 0), (7, 4)),
          ((6, 4), (2, 0)),
          ((0, 9), (2, 9)),
          ((3, 4), (1, 4)),
          ((0, 0), (8, 8)),
          ((5, 5), (8, 2))
        )

        one.run(data) mustEqual 5
      }

      "main test file" in {
        val data_puzzle_input_one = parseData.run("data_puzzle_input_one")

        one.run(data_puzzle_input_one) mustEqual 6548
      }
    }
  }
}
