import Main.Elem
import scala.collection.mutable.Stack
import scala.collection.mutable.ArrayBuffer

def run(
    data: loadData.Parsed
)(lowPoint: (Int, Int, Int)): Set[(Int, Int, Int)] = {

  val initialPoint = Elem(lowPoint._1, lowPoint._2, lowPoint._3)

  val getAdjacentLocations = minpoint.getAdjacentLocations(data.data)

  val expandingAdjacentLocations =
    Stack[Elem](initialPoint)

  getAdjacentLocations(initialPoint.x, initialPoint.y)
    .filterNot(x => x.elem == 9)
    .foreach(x => expandingAdjacentLocations.push(x))

  val res = ArrayBuffer[Elem]()

  while (expandingAdjacentLocations.nonEmpty) {
    val head = expandingAdjacentLocations.pop()
    val nextAdjLoc = getAdjacentLocations(head.x, head.y)
      .filterNot(x => x.elem == 9)
      .filterNot(x => res.contains(x))

    res += head
    nextAdjLoc.foreach(x => expandingAdjacentLocations.push(x))

  }

  res.toSet.map(_.toTuple)
}
