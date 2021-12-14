// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class ParseDataSpec extends AnyFreeSpec {

  "run" - {
    "reads the test file and returns the parsed data" in {
      parseData.run("data_test_one") mustEqual List(
        ((0, 9), (5, 9)),
        ((8, 0), (0, 8)),
        ((9, 4), (3, 4)),
        ((2, 2), (2, 1)),
        ((7, 0), (7, 4)),
        ((6, 4), (2, 0)),
        ((0, 9), (2, 9)),
        ((3, 4), (1, 4)),
        ((0, 0), (8, 8)),
        ((5, 5), (8, 2))
      )
    }
  }

  "parseLineDetails" in {
    parseData.parseLineDetails("694,732 -> 290,328") mustEqual ((694, 732), (290, 328))
  }

}
