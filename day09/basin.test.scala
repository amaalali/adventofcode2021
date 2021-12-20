// using lib org.scalatest::scalatest:3.2.10

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class BasinSpec extends AnyFreeSpec {

  val testData = loadData.run("test_data")

  "run" - {
    "top-left basin, size 3" in {
      val result = basin.run(testData)((1, 1, 0))

      result mustEqual Set(
        (1, 1, 0),
        (2, 0, 0),
        (3, 0, 1)
      )
    }

    "top-right basin, size 9" in {
      val result = basin.run(testData)((0, 9, 0))

      result mustEqual Set(
        (0, 9, 0),
        (1, 8, 0),
        (2, 7, 0),
        (3, 6, 0),
        (4, 5, 0),
        (4, 6, 1),
        (2, 8, 1),
        (1, 9, 1),
        (2, 9, 2)
      )
    }
  }

}
