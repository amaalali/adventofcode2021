def run(data: loadData.Parsed): Int = {
  val polymerizationData =
    polymerization.run(data, 10).foldLeft(Map.empty[Char, Int]) { (acc, next) =>
      acc ++ Map(next -> acc.get(next).map(_ + 1).getOrElse(0))
    }

  val l = polymerizationData.maxBy(_._2)._2
  val r = polymerizationData.minBy(_._2)._2

  l - r
}
