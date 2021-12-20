// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class LoadDataSpec extends AnyFreeSpec {

  "run" - {
    "reads the test file and returns the parsed data" in {
      val result = loadData.run("test_data")

      result.xLength mustEqual 10
      result.yLength mustEqual 5
      result.data.head mustEqual Seq(2, 1, 9, 9, 9, 4, 3, 2, 1, 0)
      result.data.last mustEqual Seq(9, 8, 9, 9, 9, 6, 5, 6, 7, 8)
    }
  }
}
