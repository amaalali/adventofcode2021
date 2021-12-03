// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class TwoSpec extends AnyFreeSpec {
  val testData = Source
    .fromResource("data_test_one")
    .getLines
    .toSeq

  "filterForO2RatingForPostion" - {
    val result_one = two.filterForO2RatingForPostion(testData, 0)
    val result_two = two.filterForO2RatingForPostion(result_one, 1)
    val result_three = two.filterForO2RatingForPostion(result_two, 2)
    val result_four = two.filterForO2RatingForPostion(result_three, 3)
    val result_five = two.filterForO2RatingForPostion(result_four, 4)

    "filters for first postion" in {
      result_one contains theSameElementsAs(
        Seq(
          "11110",
          "10110",
          "10111",
          "10101",
          "11100",
          "10000",
          "11001"
        )
      )
    }

    "filters for second position" in {
      result_two contains theSameElementsAs(
        Seq(
          "10110",
          "10111",
          "10101",
          "10000"
        )
      )
    }

    "3r pos" in {
      result_three mustEqual Seq(
        "10110",
        "10111",
        "10101"
      )
    }

    "4th pos" in {
      result_four contains theSameElementsAs(
        Seq(
          "0110",
          "10111",
          "10101"
        )
      )
    }

    "5th pos" in {
      result_five mustEqual Seq(
        "10111"
      )
    }
  }

  "oxygenGeneratorRating" in {
    val result = two.oxygenGeneratorRating(testData)

    result mustEqual 23
  }

  "cO2ScrubberRating" in {
    val result = two.cO2ScrubberRating(testData)

    result mustEqual 10
  }

  "run" - {
    "quality value" in {
      val result = two.run(testData)

      result mustEqual 230
    }
  }
}
