// using resource "./data"

import scala.io.Source

object Main extends App {

  case class Elem(elem: Int, x: Int, y: Int) {
    lazy val toTuple: (Int, Int, Int) = (elem, x, y)
  }

  def data = loadData.run("puzzle_data")
  println("Part one: " + one.run(data))
  println("Part two: " + two.run(data))
}
