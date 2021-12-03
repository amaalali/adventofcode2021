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

  "computeGammaRate" in {
    one.computeGammaRate(testData) mustEqual List("1", "0", "1", "1", "0")
  }

  "computeEpsionRate" in {
    one.computeEpsilonRate(testData) mustEqual List("0", "1", "0", "0", "1")
  }

  "run" - {
    "returns the product of gamma rate and epsilon Rate" in {
      val result = one.run(testData)
      result mustEqual 198
    }
  }
}
