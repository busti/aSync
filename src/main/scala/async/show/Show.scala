package async.show

import async.effects.Effect
import async.fixtures.Fixture
import async.util.OlaHelper

import scala.collection.mutable

class Show {
  val ola = OlaHelper.olaClient

  var effects = Seq[Effect]()
}
