// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class LoadDataSpec extends AnyFreeSpec {

  "run" - {
    "reads the test file and returns the parsed data" in {
      loadData.run("test_data") mustEqual ???
    }
  }
}
