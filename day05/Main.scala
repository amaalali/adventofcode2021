// using resource "./data"

import scala.io.Source

object Main extends App {

  val data_puzzle_input_one = parseData.run("data_puzzle_input_one")

  println("Part one: " + one.run(data_puzzle_input_one))

  val data_puzzle_input_two = data_puzzle_input_one
  println("Part two: " + two.run(data_puzzle_input_two))
}
