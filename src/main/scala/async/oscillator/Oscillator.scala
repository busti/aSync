package async.oscillator

class Oscillator(filter: Float) {
  var last   = System.currentTimeMillis()
  var period = 0d
  var offset = 0d

  def tap() {
    val now    = System.currentTimeMillis()
    val latest = now - last
    period     = period * filter + latest * (1 - filter)
    offset     = now % period * filter + offset * (1 - filter)
    last = now
  }

  def getVal: Double = {
    val now = System.currentTimeMillis()
    (now + offset) % period / period
  }
}