import scala.io.Source

def run(fileName: String): Seq[Int] =
  Source
    .fromResource(fileName)
    .getLines
    .next
    .split(",")
    .map(_.toInt)

def indexByInternalTimer(fileName: String): Map[Int, BigInt] =
  (0 to 8).map(_ -> BigInt(0)).toMap ++
    run(fileName).groupMapReduce(identity)(x => BigInt(1))(_ + _)
