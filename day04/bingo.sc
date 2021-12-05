type Row = Seq[Option[Int]]

case class BingoBoard private (
    r1: Row,
    r2: Row,
    r3: Row,
    r4: Row,
    r5: Row,
    columns: Seq[Seq[Option[Int]]]
) {

  val debug = r2.head.contains(10)

  private lazy val _columns: Seq[Seq[Option[Int]]] = {
    if (columns.nonEmpty) {
      columns
    } else {
      r1.zip(r2).zip(r3).zip(r4).zip(r5).map {
        case ((((e1, e2), e3), e4), e5) =>
          Seq(e1, e2, e3, e4, e5)
      }
    }
  }

  lazy val unmarked: Seq[Int] = Seq(r1, r2, r3, r4, r5).flatMap(_.flatten)

  lazy val isComplete =
    r1.flatten.isEmpty ||
      r2.flatten.isEmpty ||
      r3.flatten.isEmpty ||
      r4.flatten.isEmpty ||
      r5.flatten.isEmpty ||
      _columns.map(_.flatten).find(_.isEmpty).isDefined

  private def removeValueFromRow(value: Int, row: Row): Row =
    row.map(x => if (x.contains(value)) None else x)

  def markSquare(value: Int): BingoBoard =
    new BingoBoard(
      removeValueFromRow(value, r1),
      removeValueFromRow(value, r2),
      removeValueFromRow(value, r3),
      removeValueFromRow(value, r4),
      removeValueFromRow(value, r5),
      _columns.map(removeValueFromRow(value, _))
    )
}

object BingoBoard {

  def apply(
      r1: Seq[Int],
      r2: Seq[Int],
      r3: Seq[Int],
      r4: Seq[Int],
      r5: Seq[Int]
  ): BingoBoard =
    new BingoBoard(
      r1.map(Some(_)),
      r2.map(Some(_)),
      r3.map(Some(_)),
      r4.map(Some(_)),
      r5.map(Some(_)),
      Seq.empty
    )

}
