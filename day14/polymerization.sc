import scala.annotation.tailrec
import util.chaining.*

def computeFrequency[A](xs: IterableOnce[A]): Map[A, BigInt] = {
  xs.foldLeft(Map.empty[A, BigInt]) { (acc, next) =>
    acc ++ Map(next -> acc.get(next).map(_ + 1).getOrElse(BigInt(1)))
  }
}

def runfast(
    data: loadData.Parsed2,
    numberOfInterations: Int
): Map[String, BigInt] = {

  val doLog = numberOfInterations == 4

  @tailrec
  def loop(
      count: Map[String, BigInt],
      iterationCount: Int
  ): Map[String, BigInt] = {
    if (iterationCount == 0) {
      count
    } else {

      val nextCount = count
        .map {
          case origElemCount @ (elem, count) => {
            data.rules
              .get(elem)
              .map(splitElems => splitElems.map(se => (se, count)))
              .getOrElse(Seq(origElemCount))
          }
        }
        .flatten
        .foldLeft(Map.empty[String, BigInt]) { case (acc, (elem, count)) =>
          val elemTotalCount = count + acc.get(elem).getOrElse(BigInt(0))
          acc ++ Map((elem -> elemTotalCount))
        }

      loop(nextCount, iterationCount - 1)
    }
  }

  val initialCount = computeFrequency(data.template.sliding(2, 1))

  loop(initialCount, numberOfInterations)

}

def run(data: loadData.Parsed, numberOfInterations: Int): String = {
  if (numberOfInterations == 0) {
    data.template
  } else {
    run(data.copy(template = runOnce(data)), numberOfInterations - 1)
  }
}

def runOnce(data: loadData.Parsed): String = {
  data.template.head.toString ++ data.template
    .sliding(2, 1)
    .map(elementInsertion(data.rules))
    .foldLeft("")((acc, next) => {
      acc ++ next.tail
    })
}

def elementInsertion(rules: Map[String, String])(testString: String): String =
  testString.slice(0, 1) +
    rules.lift(testString).getOrElse("")
    + testString.slice(1, 2)
