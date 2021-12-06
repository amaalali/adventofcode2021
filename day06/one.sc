import lanternfish._

def run(data: Seq[Int])(days: Int): Int = {
  def ageLoop(
      previousDayLanterns: Seq[Lanternfish],
      dayCount: Int
  ): Seq[Lanternfish] = {
    if (dayCount == 0) {
      previousDayLanterns
    } else {
      ageLoop(
        previousDayLanterns
          .map(_.incrementDay())
          .flatMap { case (x, y) =>
            List(Option(x), y)
          }
          .flatten,
        dayCount - 1
      )

    }
  }

  ageLoop(data.map(Lanternfish(_)), days).length
}
