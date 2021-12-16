// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class TwoSpec extends AnyFreeSpec {
  val testData = loadData.run2("test_data")

  "run" - {
    "applies the polymerization for the number of steps and works out the difference in the most common element - the least commone element" in {
      val result = two.run(testData)

      result mustEqual BigInt("2188189693529")
    }
  }
}
