def run(data: loadData.Parsed2): BigInt = {
  val polymerizationFrequencyPairs: Map[String, BigInt] =
    polymerization.runfast(data, 40)

  val polymerizationFrequencies =
    polymerizationFrequencyPairs.toSeq
      .flatMap { case (k, v) =>
        k.split("").map(x => x.toString -> v)
      }
      .foldLeft(Map.empty[String, BigInt]) { case (acc, (k, v)) =>
        acc ++ Map(k -> acc.get(k).map(_ + v).getOrElse(v))
      }
      .map { case (k, v) =>
        (k, v / 2)
      }

  val l = polymerizationFrequencies.maxBy(_._2)._2
  val r = polymerizationFrequencies.minBy(_._2)._2

  l - r + 1 // plus one since first char is lost
}
