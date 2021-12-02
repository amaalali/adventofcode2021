def run(data: Seq[Int]): Int = {
  val slidingWindowSum: Seq[Int] = data
    .sliding(3)
    .map(_.sum)
    .toSeq

  one.run(slidingWindowSum)
}
