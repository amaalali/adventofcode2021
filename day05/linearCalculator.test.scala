// using lib org.scalatest::scalatest:3.2.10

import scala.io.Source

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers._

class LinearCalculatorSpec extends AnyFreeSpec {
  "pointsOnLine" - {
    "for a horizontal line, returns point on the line for the given range" in {
      linearCalculator.pointsOnLine(
        (9, 7),
        (7, 7)
      ) must contain theSameElementsAs (List(
        (9, 7),
        (8, 7),
        (7, 7)
      ))
    }

    "for a vertical line, returns point on the line for the given range" in {
      linearCalculator.pointsOnLine(
        (9, 1),
        (9, 5)
      ) must contain theSameElementsAs (List(
        (9, 1),
        (9, 2),
        (9, 3),
        (9, 4),
        (9, 5)
      ))
    }

    "for lines with non-constant gradient, returns the points on the line for the given range" in {
      linearCalculator.pointsOnLine(
        (9, 7),
        (7, 9)
      ) must contain theSameElementsAs (List(
        (9, 7),
        (8, 8),
        (7, 9)
      ))
    }
  }

  "intersection" - {
    "when both lines are horizontal" - {
      "when lines are colinear, then return values where their ranges intersect" in {
        val line1 = ((0, 9), (5, 9))
        val line2 = ((0, 9), (2, 9))

        linearCalculator.intersection(line1, line2, false) mustEqual Seq(
          (0, 9),
          (1, 9),
          (2, 9)
        )
      }

      "when lines non-colinear, then return no values" in {
        val line1 = ((9, 7), (7, 7))
        val line2 = ((9, 6), (7, 6))

        linearCalculator.intersection(line1, line2, false) mustEqual Seq.empty
      }
    }

    "when both lines are vertical" - {
      "when lines are colinear, then return values where their ranges intersect" in {
        val line1 = ((9, 0), (9, 5))
        val line2 = ((9, 0), (9, 2))

        linearCalculator.intersection(line1, line2, false) mustEqual Seq(
          (9, 0),
          (9, 1),
          (9, 2)
        )
      }

      "when lines are non-colinear, then return no values" in {
        val line1 = ((9, 7), (9, 10))
        val line2 = ((8, 6), (8, 10))

        linearCalculator.intersection(line1, line2, false) mustEqual Seq.empty
      }
    }

    "when there is a horizontal and vertical line" - {
      "when the lines have overlapping ranges, returns the x coordinate of the vertical line and y coordinate of the horizontal line" in {
        val verticalLine = ((1, 2), (1, 10))
        val horizontalLine = ((0, 3), (10, 3))

        linearCalculator.intersection(
          verticalLine,
          horizontalLine,
          false
        ) mustEqual Seq((1, 3))

        linearCalculator.intersection(
          horizontalLine,
          verticalLine,
          false
        ) mustEqual Seq((1, 3))
      }

      "when the lines don't have overlapping x-ranges, then return no values" in {
        val verticalLine = ((7, 2), (7, 10))
        val horizontalLine = ((1, 3), (5, 3))
        val horizontalLine2 = ((8, 4), (9, 4))

        linearCalculator.intersection(
          verticalLine,
          horizontalLine,
          false
        ) mustEqual Seq.empty

        linearCalculator.intersection(
          horizontalLine,
          verticalLine,
          false
        ) mustEqual Seq.empty

        linearCalculator.intersection(
          verticalLine,
          horizontalLine2,
          false
        ) mustEqual Seq.empty

        linearCalculator.intersection(
          horizontalLine2,
          verticalLine,
          false
        ) mustEqual Seq.empty
      }

      "when the lines don't have overlapping y-ranges, then return no values" in {
        val verticalLine = ((4, 2), (4, 9))
        val verticalLine2 = ((9, 3), (9, 5))
        val horizontalLine = ((5, 3), (8, 3))

        linearCalculator.intersection(
          verticalLine,
          horizontalLine,
          false
        ) mustEqual Seq.empty

        linearCalculator.intersection(
          horizontalLine,
          verticalLine,
          false
        ) mustEqual Seq.empty

        linearCalculator.intersection(
          verticalLine2,
          horizontalLine,
          false
        ) mustEqual Seq.empty

        linearCalculator.intersection(
          horizontalLine,
          verticalLine2,
          false
        ) mustEqual Seq.empty
      }

      "when the lines don't have overlapping x or y ranges, then return no values" in {
        val verticalLine = ((7, 5), (7, 7))
        val horizontalLine = ((1, 3), (5, 3))

        linearCalculator.intersection(
          verticalLine,
          horizontalLine,
          false
        ) mustEqual Seq.empty

        linearCalculator.intersection(
          horizontalLine,
          verticalLine,
          false
        ) mustEqual Seq.empty
      }
    }

    "when there are 2 inclined lines" - {
      "when the lines are colinear" in {
        val line1 = ((8, 0), (0, 8))
        val line2 = ((6, 4), (2, 0))

        linearCalculator.intersection(
          line1,
          line2,
          true
        ) mustEqual Seq((5, 3))

        linearCalculator.intersection(
          line2,
          line1,
          true
        ) mustEqual Seq((5, 3))
      }
    }
  }
}
