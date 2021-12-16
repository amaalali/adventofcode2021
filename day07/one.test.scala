// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class OneSpec extends AnyFreeSpec {
  val testData = loadData.run("test_data")

  "run" - {
    "test" in {
      val result = one.run(testData)
      result mustEqual 37
    }
  }
}
