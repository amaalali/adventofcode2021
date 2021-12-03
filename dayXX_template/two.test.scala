// using resource "./data_test"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

import two.Dir

class TwoSpec extends AnyFreeSpec {
  val testData = Source
    .fromFile("/Users/amaalali/code/adventofcode2021/dayXX/data_test_one")
    .getLines
    .toSeq

  // "run" - {
  //   "" in {
  //     val result = two.run(testData)

  //     result mustEqual 900
  //   }
  // }
}
