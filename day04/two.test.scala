// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class TwoSpec extends AnyFreeSpec {
  val testData = Source
    .fromResource("data_test_one")

  val (draws, puzzles) = parseBingoData.run(testData)

  "run" - {
    "returns the product of sum of the unmarked numbers in the puzzle and the last successfult value" in {
      val result = two.run(draws, puzzles)
      result mustEqual 1924
    }
  }
}
