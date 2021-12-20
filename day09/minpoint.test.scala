// using lib org.scalatest::scalatest:3.2.10

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class MinpointSpec extends AnyFreeSpec {

  val testData = loadData.run("test_data")

  "run" - {
    "test" in {
      val result = minpoint.run(testData)

      result mustEqual Seq(
        (1, 1, 0),
        (0, 9, 0),
        (5, 2, 2),
        (5, 6, 4)
      )
    }
  }

}
