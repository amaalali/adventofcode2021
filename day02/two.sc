case class Dir(forward: Int, dept: Int, aim: Int) {

  def nav(str: String): Dir = {
    if (str.contains("down")) {
      goDown(str.drop(4 + 1).toInt)
    } else if (str.contains("up")) {
      goUp(str.drop(2 + 1).toInt)
    } else {
      // forward
      goForward(str.drop(7 + 1).toInt)
    }
  }

  private def goDown(value: Int): Dir =
    copy(aim = aim + value)

  private def goUp(value: Int): Dir =
    copy(aim = aim - value)

  private def goForward(value: Int): Dir =
    copy(forward = forward + value, dept = dept + (value * aim))

}

def run(data: Seq[String]): Int = {
  val dir @ Dir(f, d, aim) =
    data.foldLeft(Dir(0, 0, 0))((dir, navInstruction) =>
      dir.nav(navInstruction)
    )

  f * d
}
