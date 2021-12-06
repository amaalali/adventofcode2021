case class Lanternfish(internalTimer: Int) {

  def incrementDay(): (Lanternfish, Option[Lanternfish]) =
    if (internalTimer == 0) {
      (copy(6), Some(Lanternfish(8)))
    } else {
      (copy(internalTimer = internalTimer - 1), None)
    }

}
