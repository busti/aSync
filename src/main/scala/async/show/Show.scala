package async.show

import async.effects.Effect
import async.util.OlaHelper
import de.sciss.osc.TCP

class Show {
  val ola = OlaHelper.olaClient

  var effects = Seq[Effect]()
}
