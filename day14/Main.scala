// using resource "./data"

import scala.io.Source

object Main extends App {
  val data1 = loadData.run("puzzle_data")
  println("Part one: " + one.run(data1))
  val data2 = loadData.run2("puzzle_data")
  println("Part two: " + two.run(data2))
}
