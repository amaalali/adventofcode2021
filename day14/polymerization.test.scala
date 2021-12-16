// using lib org.scalatest::scalatest:3.2.10

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class PolymerizationSpec extends AnyFreeSpec {
  "runOnce" - {
    "replaces the string pairs with new string specified in the rule" - {
      "when there are no rules" in {
        val data = loadData.Parsed(
          "NNCB",
          Map.empty
        )

        val result = polymerization.runOnce(data)
        result mustEqual "NNCB"
      }

      "when there are no matching rules" in {
        val data = loadData.Parsed(
          "NNCB",
          Map(
            "XY" -> "1"
          )
        )

        val result = polymerization.runOnce(data)
        result mustEqual "NNCB"
      }

      "when there is only one matching rule" in {
        val data = loadData.Parsed(
          "NNCB",
          Map(
            "NN" -> "C"
          )
        )

        val result = polymerization.runOnce(data)
        result mustEqual "NCNCB"
      }

      "when there are multiple matching rules" in {

        val data = loadData.Parsed(
          "NNCB",
          Map(
            "NN" -> "C",
            "NC" -> "B",
            "CB" -> "H"
          )
        )

        val result = polymerization.runOnce(data)
        result mustEqual "NCNBCHB"
      }
    }
  }

  "elementInsertion" - {
    "when there are no a matching patterns" in {
      val result = polymerization.elementInsertion(
        Map(
          "NA" -> "C"
        )
      )("NN")

      result mustEqual "NN"
    }

    "when there is a matching pattern" in {
      val result = polymerization.elementInsertion(
        Map(
          "NN" -> "C"
        )
      )("NN")

      result mustEqual "NCN"
    }
  }

  "run" - {
    val data = loadData.run("test_data")

    "one step" in {
      polymerization.run(data, 1) mustEqual "NCNBCHB"
    }

    "two steps" in {
      polymerization.run(data, 2) mustEqual "NBCCNBBBCBHCB"
    }

    "three steps" in {
      polymerization.run(data, 3) mustEqual "NBBBCNCCNBBNBNBBCHBHHBCHB"
    }

    "four steps" in {
      polymerization.run(
        data,
        4
      ) mustEqual "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"
    }

    "five steps" in {
      polymerization
        .run(
          data,
          5
        )
        .length mustEqual 97
    }

    "10 steps" in {
      polymerization
        .run(
          data,
          10
        )
        .length mustEqual 3073
    }
  }
}
