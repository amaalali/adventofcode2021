// using lib org.scalatest::scalatest:3.2.10

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class TestSuite extends AnyFreeSpec {
  "run" - {
    "returns an empty string" in {
      val obtained = logic.run()
      obtained mustEqual "entry function"
    }
  }
}
