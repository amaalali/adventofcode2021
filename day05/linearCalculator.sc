import parseData.{Line, Point}

def intersection(
    l1: Line,
    l2: Line,
    includeDiagonals: Boolean
): Seq[Point] = {
  (verticalColinearIntersection orElse
    horizontalColinearIntersection orElse
    horizontalVerticalIntersection orElse
    intersectionWithInclinedLine(includeDiagonals) orElse
    nonIntersectingLines)(l1, l2)
}

def diagonalFilter(
    includeDiagonals: Boolean
): PartialFunction[(Line, Line), (Line, Line)] = {
  case ls @ (_, _) if includeDiagonals => ls
}

def isVertical(l: Line) = l._1._1 == l._2._1

def isHorzontal(l: Line) = l._1._2 == l._2._2

def isInclined(l: Line) = !(isVertical(l) || isHorzontal(l))

def sortX(l: Line): (Int, Int) = {
  val xMin :: xMax :: Nil = List(l._1._1, l._2._1).sorted
  (xMin, xMax)
}

def sortY(l: Line): (Int, Int) = {
  val yMin :: yMax :: Nil = List(l._1._2, l._2._2).sorted
  (yMin, yMax)
}

def isHorizontalColinear(l1: Line, l2: Line): Boolean =
  isHorzontal(l1) && isHorzontal(l2) && (l1._2._2 == l2._2._2)

def isVerticalColinear(l1: Line, l2: Line): Boolean =
  isVertical(l1) && isVertical(l2) && (l1._1._1 == l2._1._1)

def intersectionXRange(l1: Line, l2: Line): Range = {
  val (l1XMin, l1XMax) = sortX(l1)
  val (l2XMin, l2XMax) = sortX(l2)
  val lowerLimit = List(l2XMin, l1XMin).max
  val upperLimit = List(l2XMax, l1XMax).min
  lowerLimit to upperLimit // may be empty
}

def intersectionYRange(l1: Line, l2: Line): Range = {
  val (l1YMin, l1YMax) = sortY(l1)
  val (l2YMin, l2YMax) = sortY(l2)
  val lowerLimit = List(l2YMin, l1YMin).max
  val upperLimit = List(l2YMax, l1YMax).min
  lowerLimit to upperLimit // may be empty
}

type IntersectionCalculator = PartialFunction[(Line, Line), Seq[Point]]

val horizontalColinearIntersection: IntersectionCalculator = {
  case (l1, l2) if isHorizontalColinear(l1, l2) =>
    val xRange = intersectionXRange(l1, l2)
    if (!xRange.isEmpty) {
      xRange.map(x => (x, l1._1._2))
    } else Seq.empty
}

val verticalColinearIntersection: IntersectionCalculator = {
  case (l1, l2) if isVerticalColinear(l1, l2) =>
    val yRange = intersectionYRange(l1, l2)
    if (!yRange.isEmpty) {
      yRange.map(y => (l1._1._1, y))
    } else Seq.empty
}

val horizontalVerticalIntersection: IntersectionCalculator = {
  case (l1, l2)
      if ((isHorzontal(l1) && isVertical(l2)) || (isHorzontal(l2) && isVertical(
        l1
      ))) && !intersectionXRange(l1, l2).isEmpty && !intersectionYRange(
        l1,
        l2
      ).isEmpty =>
    if (isHorzontal(l1)) {
      Seq((l2._1._1, l1._1._2))
    } else {
      Seq((l1._1._1, l2._1._2))
    }
}

val intersectionWithInclinedLine: Boolean => IntersectionCalculator =
  includeDiagonals => {
    case (l1, l2) if isInclined(l1) || isInclined(l2) => {

      if (!includeDiagonals) {
        Seq.empty
      } else {
        (isInclined(l1), isInclined(l2)) match {
          case (true, false) =>
            intersection(l2, l2, false).toSet
              .intersect(pointsOnLine(l1._1, l1._2).toSet)
              .toList

          case (false, true) =>
            intersection(l1, l1, false).toSet
              .intersect(pointsOnLine(l2._1, l2._2).toSet)
              .toList

          case (true, true) =>
            pointsOnLine(l1._1, l1._2).toSet
              .intersect(pointsOnLine(l2._1, l2._2).toSet)
              .toList
        }
      }
    }
  }

val nonIntersectingLines: IntersectionCalculator = { case _ =>
  Seq.empty
}

def pointsOnLine(p1: Point, p2: Point): Seq[Point] = {
  val xMin :: xMax :: Nil = List(p1._1, p2._1).sorted

  if (xMin == xMax) {
    val yMin :: yMax :: Nil = List(p1._2, p2._2).sorted
    (yMin to yMax).map(y => (xMin, y))
  } else {
    val m = (p2._2 - p1._2) / (p2._1 - p1._1)
    val c = p1._2 - (m * p1._1)

    (xMin to xMax).map { x =>
      (x, m * x + c)
    }
  }
}
