// using resource "./data"

import scala.io.Source

object Main extends App {
  val data_puzzle_input_one = Source
    .fromResource("data_puzzle_input_one")
    .getLines
    .map(_.toInt)
    .toSeq

  println("Part one: " + one.run(data_puzzle_input_one))

  val data_puzzle_input_two: Seq[Int] = data_puzzle_input_one
  println("Part two: " + two.run(data_puzzle_input_two))
}
