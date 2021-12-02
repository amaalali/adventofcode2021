def run(data: Seq[Int]): Int = {
  data
    .sliding(2)
    .filter {
      case h :: t :: _ => h < t
      case _           => false
    }
    .length
}
