import scala.annotation.tailrec

def run(data: Seq[String]): Int =
  oxygenGeneratorRating(data) * cO2ScrubberRating(data)

def filterForO2RatingForPostion(
    data: Seq[String],
    position: Int
): Seq[String] = {
  val gammaForLoop = one.computeGammaRate(data)(position)
  data.filter(_.charAt(position).toString() == gammaForLoop)
}

def oxygenGeneratorRating(data: Seq[String]): Int = {
  @tailrec
  def loop(filteredData: Seq[String], position: Int): String = {
    filterForO2RatingForPostion(filteredData, position) match {
      case h :: Nil => h
      case d        => loop(d, position + 1)
    }
  }

  val result = loop(data, 0)

  one.binarySeqToInt(result.split("").toSeq)
}

def filterForCO2RatingForPostion(
    data: Seq[String],
    position: Int
): Seq[String] = {
  val epsilonForLoop = one.computeEpsilonRate(data)(position)
  data.filter(_.charAt(position).toString() == epsilonForLoop)
}

def cO2ScrubberRating(data: Seq[String]): Int = {
  @tailrec
  def loop(filteredData: Seq[String], position: Int): String = {
    filterForCO2RatingForPostion(filteredData, position) match {
      case h :: Nil => h
      case d        => loop(d, position + 1)
    }
  }

  val result = loop(data, 0)

  one.binarySeqToInt(result.split("").toSeq)
}
