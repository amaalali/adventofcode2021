import scala.io.Source

case class Parsed(
    template: String,
    rules: Map[String, String]
)

def run(fileName: String): Parsed = {
  val dataLines = Source.fromResource(fileName).getLines

  val input = dataLines.next

  dataLines.next // throw away empty line

  val rules = dataLines
    .map(line =>
      val lineArr = line.split("->").map(_.trim)
      val pair = lineArr(0)
      val newChar = lineArr(1)
      // pair -> (pair.slice(0, 1) + newChar + pair.slice(1, 2))
      pair -> newChar
    )
    .toMap

  Parsed(input, rules)
}

case class Parsed2(
    template: String,
    rules: Map[String, Seq[String]]
)

def run2(fileName: String): Parsed2 = {
  val dataLines = Source.fromResource(fileName).getLines

  val input = dataLines.next

  dataLines.next // throw away empty line

  val rules = dataLines
    .map(line =>
      val lineArr = line.split("->").map(_.trim)
      val pair = lineArr(0)
      val newChar = lineArr(1)
      pair -> Seq(pair.slice(0, 1) + newChar, newChar + pair.slice(1, 2))
    )
    .toMap

  Parsed2(input, rules)
}
