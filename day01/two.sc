// using lib com.lihaoyi::os-lib:0.7.8

def run(data: Seq[Int]): Int = {
  val slidingWindowSum: Seq[Int] = data
    .sliding(3)
    .map(_.sum)
    .toSeq

  one.run(slidingWindowSum)
}
