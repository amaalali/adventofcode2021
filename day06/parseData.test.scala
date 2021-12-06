// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class ParseDataSpec extends AnyFreeSpec {

  "run" - {
    "reads the test file and returns the parsed data" in {
      parseData.run("data_test_one") mustEqual List(3, 4, 3, 1, 2)
    }
  }

  "indexByInternalTimer" in {
    parseData.indexByInternalTimer("data_test_one") mustEqual Map(
      0 -> BigInt(0),
      1 -> BigInt(1),
      2 -> BigInt(1),
      3 -> BigInt(2),
      4 -> BigInt(1),
      5 -> BigInt(0),
      6 -> BigInt(0),
      7 -> BigInt(0),
      8 -> BigInt(0)
    )
  }
}
