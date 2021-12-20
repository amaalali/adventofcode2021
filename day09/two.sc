def run(data: loadData.Parsed): Int =
  minpoint
    .run(data)
    .map(basin.run(data))
    .map(_.size)
    .sorted(Ordering.Int.reverse)
    .take(3)
    .foldLeft(1)(_ * _)
