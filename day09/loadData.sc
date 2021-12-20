import scala.io.Source

case class Data(xLength: Int, yLength: Int, data: Seq[Seq[Int]])
type Parsed = Data

def run(fileName: String): Parsed = {
  val data = Source
    .fromResource(fileName)
    .getLines
    .map(
      _.split("").map(_.toInt).toSeq
    )
    .toSeq

  Data(data.head.length, data.length, data)
}
