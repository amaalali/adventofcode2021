// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class OneSpec extends AnyFreeSpec {
  val testData = Source
    .fromResource("data_test_one")
    .getLines
    .toSeq

  "sumForward" - {
    "sum all of the magnitude values for all the forwards in input source" - {
      "when there is one forward" in {
        val testData = Seq(
          "forward 3"
        )

        one.sumForward(testData) mustEqual 3
      }

      "when there are multiple forward" in {
        val testData = Seq(
          "forward 3",
          "forward 24"
        )

        one.sumForward(testData) mustEqual 27
      }

      "when there are multiple forward and other directions," in {
        val testData = Seq(
          "forward 3",
          "up 2",
          "forward 24",
          "down 5"
        )

        one.sumForward(testData) mustEqual 27
      }
    }
  }

  "deptSum" - {
    "sum all of the magnitude values for all the forwards in input source" - {
      "when there are only down directions" in {
        val testData = Seq(
          "down 3"
        )

        one.sumDepth(testData) mustEqual 3
      }

      "when there are multiple down" in {
        val testData = Seq(
          "down 3",
          "down 24"
        )

        one.sumDepth(testData) mustEqual 27
      }

      "when there are only up directions" in {
        val testData = Seq(
          "up 2"
        )

        one.sumDepth(testData) mustEqual -2
      }

      "when there are multiple up" in {
        val testData = Seq(
          "up 3",
          "up 24"
        )

        one.sumDepth(testData) mustEqual -27
      }

      "when there are up and down" in {
        val testData = Seq(
          "up 7",
          "down 6",
          "down 8",
          "up 4"
        )

        one.sumDepth(testData) mustEqual 3
      }

      "when there are up and down, and other directions" in {
        val testData = Seq(
          "up 7",
          "forward 2",
          "down 6",
          "forward 4",
          "down 8",
          "up 4"
        )

        one.sumDepth(testData) mustEqual 3
      }
    }
  }

  "run" - {
    "retrns the product of the sum of forwards and the sum of the dept" in {
      val result = one.run(testData)

      result mustEqual 150
    }
  }
}
