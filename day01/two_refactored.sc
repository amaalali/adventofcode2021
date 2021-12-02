val slidingWindowSum: Seq[Int] => Seq[Int] =
  _.sliding(3)
    .map(_.sum)
    .toSeq

def run(data: Seq[Int]): Int =
  (one.run compose slidingWindowSum)(data)
