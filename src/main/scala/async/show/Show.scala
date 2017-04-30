package async.show

import async.effects.Effect
import async.fixtures.Fixture

import scala.collection.mutable

class Show(val fixtures: Seq[Fixture]) {
  val effects = mutable.Seq[Effect[Any, Any]]()

}
