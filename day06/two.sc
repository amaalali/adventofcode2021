def run(data: Map[Int, BigInt])(days: Int): BigInt = {

  def ageLoop(
      dayCount: Int,
      previousDayLanterns: Map[Int, BigInt]
  ): Map[Int, BigInt] = {
    if (dayCount == 0) {
      previousDayLanterns
    } else {
      val asdf =
        ((0 to 5).toList :+ 7).foldLeft(Map.empty[Int, BigInt]) { (data, age) =>
          data + (age -> previousDayLanterns(age + 1))
        } ++ Map(
          6 -> (previousDayLanterns(7) + previousDayLanterns(0)),
          8 -> previousDayLanterns(0)
        )

      ageLoop(
        dayCount - 1,
        asdf
      )
    }
  }

  ageLoop(days, data).foldLeft(BigInt(0)) { case (acc, (_, count)) =>
    acc + count
  }
}
