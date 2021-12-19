private def parseWireSignals(wireSignal: String): Option[Int] = {
  (wireSignal, wireSignal.length) match {
    case (_, 2) => Some(1)
    case (_, 4) => Some(4)
    case (_, 3) => Some(7)
    case (_, 7) => Some(8)
    case _      => None
  }
}

def run(data: loadData.Parsed): Int =
  data.flatMap(_.flatMap(parseWireSignals)).length
