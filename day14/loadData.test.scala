// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class LoadDataSpec extends AnyFreeSpec {

  "run" - {
    val result = loadData.run("test_data")

    "reads template value from the file" in {
      result.template mustEqual "NNCB"
    }

    "reads pair insertion rules from the file" in {
      result.rules mustEqual Map(
        "CH" -> "B",
        "HH" -> "N",
        "CB" -> "H",
        "NH" -> "C",
        "HB" -> "C",
        "HC" -> "B",
        "HN" -> "C",
        "NN" -> "C",
        "BH" -> "H",
        "NC" -> "B",
        "NB" -> "B",
        "BN" -> "B",
        "BB" -> "N",
        "BC" -> "B",
        "CC" -> "N",
        "CN" -> "C"
      )
    }
  }

  "run2" - {
    val result = loadData.run2("test_data")

    "reads template value from the file" in {
      result.template mustEqual "NNCB"
    }

    "reads pair insertion rules from the file" in {
      result.rules mustEqual Map(
        "CH" -> Seq("CB", "BH"),
        "HH" -> Seq("HN", "NH"),
        "CB" -> Seq("CH", "HB"),
        "NH" -> Seq("NC", "CH"),
        "HB" -> Seq("HC", "CB"),
        "HC" -> Seq("HB", "BC"),
        "HN" -> Seq("HC", "CN"),
        "NN" -> Seq("NC", "CN"),
        "BH" -> Seq("BH", "HH"),
        "NC" -> Seq("NB", "BC"),
        "NB" -> Seq("NB", "BB"),
        "BN" -> Seq("BB", "BN"),
        "BB" -> Seq("BN", "NB"),
        "BC" -> Seq("BB", "BC"),
        "CC" -> Seq("CN", "NC"),
        "CN" -> Seq("CC", "CN"),
      )
    }
  }
}
