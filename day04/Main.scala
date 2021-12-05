// using resource "./data"

import scala.io.Source

object Main extends App {
  def puzzle = parseBingoData.run(
    Source
      .fromResource("data_puzzle_input_one")
  )

  val (draws1, puzzles1) = puzzle
  println("Part one: " + one.run(draws1, puzzles1))

  val (draws2, puzzles2) = puzzle
  println("Part two: " + two.run(draws2, puzzles2))
}
