// num, xpos, ypos

import Main.Elem

def getAdjacentLocations(
    data: Seq[Seq[Int]]
)(x: Int, y: Int): Seq[Elem] = {
  Seq(
    (x - 1, y),
    (x + 1, y),
    (x, y - 1),
    (x, y + 1)
  ).flatMap { case (xVal, yVal) =>
    data.lift(yVal).flatMap(_.lift(xVal).map(value => Elem(value, xVal, yVal)))
  }
}

def run(data: loadData.Parsed): Seq[(Int, Int, Int)] = {
  val arr = data.data.lift

  val adjacentLocations: Seq[(Elem, Seq[Elem])] = for {
    y <- 0 to (data.yLength - 1)
    x <- 0 to (data.xLength - 1)
  } yield {
    val curr = (arr(x), arr(y))
    val localAdjacentLocations = getAdjacentLocations(data.data)(x, y)
    (Elem(data.data(y)(x), x, y), localAdjacentLocations)
  }

  adjacentLocations
    .filter {
      case (elem @ Elem(elemValue, _, _), adj) => {
        adj
          .filterNot(_.elem > elemValue)
          .isEmpty
      }
    }
    .map(_._1.toTuple)

}
