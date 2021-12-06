// using resource "./data"

import scala.io.Source

object Main extends App {

  println("Part one: " + one.run(parseData.run("data_puzzle_input_one"))(80))
  println(
    "Part two: " + two.run(
      parseData.indexByInternalTimer("data_puzzle_input_one")
    )(256)
  )
}
