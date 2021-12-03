def run(data: Seq[String]): Int = {
  val dir @ Dir(f, d, aim) =
    data.foldLeft(Dir(0, 0, 0))((dir, navInstruction) =>
      dir.nav(navInstruction)
    )

  f * d
}
