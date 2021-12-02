// using resource "./data_test"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class OneSpec extends AnyFreeSpec {
  val testData = Source
    .fromFile("/Users/amaalali/code/adventofcode2021/day01/data_test_one")
    .getLines
    .map(_.toInt)
    .toSeq
  // val data = Source.fromResource("data_test").getLines.map(_.toInt).toSeq

  "run" - {
    "counts the number of increases in depth measurements" in {

      val result = one.run(testData)
      result mustEqual 7
    }
  }
}
