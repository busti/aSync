package async.effects

import spire.implicits._

abstract class Effect[P, R](bindings: Map[P, EffectBinding[R]]) {

  def onFrame() {

  }
}
