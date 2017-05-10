package async.effects

trait EffectBinding[T] {
  def bind(result: T) {

  }
}
