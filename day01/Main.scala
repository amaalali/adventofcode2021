// using resource "./data"

import scala.io.Source

object Main extends App {
  val data_puzzle_input_one = Source
    .fromFile(
      "/Users/amaalali/code/adventofcode2021/day01/data_puzzle_input_one"
    )
    .getLines
    .map(_.toInt)
    .toSeq

  val data_puzzle_input_two: Seq[Int] = data_puzzle_input_one

  println("Part one: " + one.run(data_puzzle_input_one))
  println("Part two: " + two.run(data_puzzle_input_two))
}
