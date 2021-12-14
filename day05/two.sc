def run(data: parseData.DataShape): Int = {
  data
    .combinations(2)
    .flatMap { case l1 :: l2 :: Nil =>
      linearCalculator.intersection(l1, l2, true)
    }
    .toList
    .toSet
    .size
}
