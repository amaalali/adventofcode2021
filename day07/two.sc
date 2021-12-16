import scala.math.abs

def run(data: loadData.Parsed): Int = {
  val max = data.max
  val min = data.min
  val range = 1 to (min + max) // 0 or 1?

  data
    .map(crabLocation => {
      range.map { horizontalPosition =>
        val distToMove = abs(horizontalPosition - crabLocation)
        (1 to distToMove).sum
      }
    })
    .foldLeft(List.fill(min + max - 1)(0)) { (acc, next) =>
      {
        acc.zip(next).map(x => x._1 + x._2)
      }
    }
    .min
}
