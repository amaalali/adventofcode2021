import scala.io.Source

type Parsed = Seq[Int]

def run(fileName: String): Parsed =
  Source
    .fromResource(fileName)
    .getLines
    .next
    .split(",")
    .map(_.toInt)
