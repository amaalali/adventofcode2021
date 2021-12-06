// using lib org.scalatest::scalatest:3.2.10

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

import lanternfish._

class LanternfishSpec extends AnyFreeSpec {
  "Lanternfish" - {
    "incrementDay" - {
      "when the internal timer is greater than zero" - {
        "returns the lanternfish aged by 1 day an no offspring" in {
          val ageValues = (1 to 8)

          ageValues.foreach { age =>
            val sut = Lanternfish(age)

            sut.incrementDay() mustEqual (Lanternfish(age - 1), None)
          }
        }
      }

      "when the internal timer is zero" - {
        "that lanernfish internal timer is set to 6 and the offspring has an internal timer of 8" in {
          val sut = Lanternfish(0)

          sut.incrementDay() mustEqual (Lanternfish(6), Some(Lanternfish(8)))
        }
      }
    }
  }
}
