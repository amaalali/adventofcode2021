import scala.io.Source

type Parsed = Seq[Seq[String]]

def run(fileName: String): Parsed =
  Source
    .fromResource(fileName)
    .getLines
    .map(
      _.split('|').last
        .split(" ")
        .filter(_.nonEmpty)
        .toSeq
    )
    .toSeq
