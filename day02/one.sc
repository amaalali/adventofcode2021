def run(data: Seq[String]): Int = {
  sumForward(data) * sumDepth(data)
}

def sumDirection(dirName: String)(data: Seq[String]): Int =
  data
    .filter(_.contains(dirName))
    .map(_.drop(dirName.length + 1))
    .map(_.toInt)
    .sum

def sumForward(data: Seq[String]): Int =
  sumDirection("forward")(data)

def sumDepth(data: Seq[String]): Int = {
  val ups = sumDirection("up")(data)
  val downs = sumDirection("down")(data)

  downs - ups
}
