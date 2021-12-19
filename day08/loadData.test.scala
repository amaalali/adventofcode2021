// using resource "./data"
// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class LoadDataSpec extends AnyFreeSpec {

  "run" - {
    "reads the test file and returns the parsed data" in {
      loadData.run("test_data") mustEqual List(
        List("fdgacbe", "cefdb", "cefbgd", "gcbe"),
        List("fcgedb", "cgb", "dgebacf", "gc"),
        List("cg", "cg", "fdcagb", "cbg"),
        List("efabcd", "cedba", "gadfec", "cb"),
        List("gecf", "egdcabf", "bgf", "bfgea"),
        List("gebdcfa", "ecba", "ca", "fadegcb"),
        List("cefg", "dcbef", "fcge", "gbcadfe"),
        List("ed", "bcgafe", "cdgba", "cbgef"),
        List("gbdfcae", "bgc", "cg", "cgb"),
        List("fgae", "cfgab", "fg", "bagce")
      )
    }
  }
}
