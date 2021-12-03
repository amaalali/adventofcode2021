def computeGammaRate(data: Seq[String]): Seq[String] = {
  val len = data.head.length
  val initialCount = Map.empty[String, Int]

  data
    .map(_.split("").toSeq)
    .foldLeft(Seq.fill(len)(initialCount))((counts, nexts) => {
      counts.zip(nexts).map { case (count, next) =>
        val currCount = count.getOrElse(next, 0)
        count ++ Map((next, currCount + 1))
      }
    })
    .map(x => {
      val first = x.head._2
      if (x.values.count(_ == first) == x.size) {
        "1"
      } else {
        x.maxBy(_._2)._1
      }
    })
}

def computeEpsilonRate(data: Seq[String]): Seq[String] = {
  def flipBit(bit: String): String = if (bit == "0") "1" else "0"
  computeGammaRate(data).map(flipBit)
}

def binarySeqToInt(bits: Seq[String]): Int =
  Integer.parseInt(bits.mkString, 2)

def run(data: Seq[String]): Int = {

  val gamma = computeGammaRate(data)
  val epsilon = computeEpsilonRate(data)

  binarySeqToInt(gamma) * binarySeqToInt(epsilon)
}
