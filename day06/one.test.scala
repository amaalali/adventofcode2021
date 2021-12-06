// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class OneSpec extends AnyFreeSpec {

  "run" - {
    "when there is a single initial lanternfish with an internal timer of 1" - {
      "returns the number of lanternfish after 1 day" in {
        val testData = List(1)
        val result = one.run(testData)(1)

        result mustEqual 1
      }

      "returns the number of lanternfish after 2 days" in {
        val testData = List(1)
        val result = one.run(testData)(2)

        result mustEqual 2
      }

      "returns the number of lanternfish after 9 days" in {
        val testData = List(1)
        val result = one.run(testData)(9)

        result mustEqual 3
      }

      "returns the number of lanternfish after 11 days" in {
        val testData = List(1)
        val result = one.run(testData)(11)

        result mustEqual 4
      }
    }

    "when there are multiple initial lanterfishes" - {
      "returns the number of lanternfish after 18 days" in {
        val testData = List(3, 4, 3, 1, 2)
        val result = one.run(testData)(18)

        result mustEqual 26
      }

      "returns the number of lanternfish after 80 days" in {
        val testData = List(3, 4, 3, 1, 2)
        val result = one.run(testData)(80)

        result mustEqual 5934
      }
    }
  }
}
