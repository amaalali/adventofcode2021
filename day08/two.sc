private def parseWireSignals(wireSignal: String): Option[String] = {
  wireSignal.toSet match {
    case x if x.size == 2                                 => Some("1")
    case x if x.size == 4                                 => Some("4")
    case x if x.size == 3                                 => Some("7")
    case x if x.size == 7                                 => Some("8")
    
    case x if x == Set('e', 'f', 'a', 'b', 'g', 'c', 'd') => Some("8")
    case x if x == Set('e', 'f', 'b', 'c', 'd')           => Some("5")
    case x if x == Set('f', 'a', 'g', 'c', 'd')           => Some("2")
    case x if x == Set('f', 'a', 'b', 'c', 'd')           => Some("3")
    case x if x == Set('d', 'a', 'b')                     => Some("7")
    case x if x == Set('e', 'f', 'a', 'b', 'c', 'd')      => Some("9")
    case x if x == Set('e', 'f', 'b', 'g', 'c', 'd')      => Some("6")
    case x if x == Set('e', 'a', 'f', 'b')                => Some("4")
    case x if x == Set('e', 'a', 'b', 'g', 'c', 'd')      => Some("0")
    case x if x == Set('a', 'b')                          => Some("1")
  }
}

def run(data: loadData.Parsed): Int = {
  val asdf = data.map(x => {
    val qwer = x.flatMap(parseWireSignals).mkString("").toInt
    println(qwer + " " + x)
    qwer
  })

  println(asdf.mkString("\n", "\n", "\n"))
  asdf.sum
}
