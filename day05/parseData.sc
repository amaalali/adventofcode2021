import scala.io.Source

type Point = (Int, Int)
type Line = (Point, Point)
type DataShape = Seq[Line]

def run(fileName: String): DataShape =
  Source
    .fromResource(fileName)
    .getLines
    .map(parseLineDetails)
    .toList

def parseLineDetails(string: String): Line = {
  val lineRegex = """(\d*),(\d*) -> (\d*),(\d*)""".r
  string match {
    case lineRegex(x1, y1, x2, y2) =>
      ((x1.toInt, y1.toInt), (x2.toInt, y2.toInt))
  }
}
