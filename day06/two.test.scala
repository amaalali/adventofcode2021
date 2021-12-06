// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class TwoSpec extends AnyFreeSpec {

  val emptyInput = (0 to 8).map(x => (x -> BigInt(0))).toList.toMap

  "run" - {
    "when there is a single initial lanternfish with an internal timer of 1" - {
      val testData = emptyInput ++ Map(1 -> BigInt(1))

      "returns the number of lanternfish after 1 day" in {
        val result = two.run(testData)(1)

        result mustEqual 1
      }

      "returns the number of lanternfish after 2 days" in {
        val result = two.run(testData)(2)

        result mustEqual 2
      }

      "returns the number of lanternfish after 9 days" in {
        val result = two.run(testData)(9)

        result mustEqual 3
      }

      "returns the number of lanternfish after 11 days" in {
        val result = two.run(testData)(11)

        result mustEqual 4
      }
    }

    "when there are multiple initial lanterfishes" - {
      val testData = emptyInput ++ Map(
        1 -> BigInt(1),
        2 -> BigInt(1),
        3 -> BigInt(2),
        4 -> BigInt(1)
      )

      "returns the number of lanternfish after 18 days" in {
        val result = two.run(testData)(18)

        result mustEqual 26
      }

      "returns the number of lanternfish after 80 days" in {
        val result = two.run(testData)(80)

        result mustEqual 5934
      }

      "returns the number of lanternfish after 256 days" in {
        val result = two.run(testData)(256)

        result mustEqual BigInt("26984457539")
      }
    }
  }
}
