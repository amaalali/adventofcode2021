// using resource "./data_test"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

import two.Dir

class TwoSpec extends AnyFreeSpec {
  val testData = Source
    .fromFile("/Users/amaalali/code/adventofcode2021/day02/data_test_one")
    .getLines
    .toSeq

  "Dir" - {
    val initial = Dir(0, 0, 0)

    "when going down" - {
      val result = initial.nav("down 3")

      "dept does not change" in {
        result.dept mustEqual 0
      }

      "increases aim" in {
        result.aim mustEqual 3
      }

      "forward does not change" in {
        result.forward mustEqual 0
      }
    }

    "when going up" - {
      val result = initial.nav("up 3")

      "dept does not change" in {
        result.dept mustEqual 0
      }

      "increases aim" in {
        result.aim mustEqual -3
      }

      "forward does not change" in {
        result.forward mustEqual 0
      }
    }

    "when going foward" - {
      "when the aim is 0" - {
        val initial = Dir(0, 0, 0)
        val result = initial.nav("forward 5")

        "dept does not change" in {
          result.dept mustEqual 0
        }

        "aim does not change" in {
          result.aim mustEqual 0
        }

        "forward increases by the forward magnitude" in {
          result.forward mustEqual 5
        }
      }

      "when the aim is non-zero" - {
        val initial = Dir(5, 5, 5)
        val result = initial.nav("forward 8")

        "dept increases by aim x forward magnitude" in {
          result.dept mustEqual 45
        }

        "aim does not change" in {
          result.aim mustEqual 5
        }

        "forward increases by the forward magnitude" in {
          result.forward mustEqual 13
        }
      }
    }
  }

  "run" - {
    "returns the product of the forward and dept using the effect of aim" in {
      val result = two.run(testData)

      result mustEqual 900
    }
  }
}
