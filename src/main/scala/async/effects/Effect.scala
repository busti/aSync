package async.effects

import spire.implicits._

abstract class Effect[P, R] {
  def onFrame(param: P): R
}
