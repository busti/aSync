package async.show

import async.effects.Effect
import async.fixtures.{Channel, Fixture, Metadata}
import async.oscillator.Oscillator
import de.sciss.osc.{Server, TCP}

class Maiparty(val osc: Server) extends Show {
  val metaPar = new Metadata("foo", "par", None, Seq(
    Channel("dimmer", None, None),
    Channel("red", None, None),
    Channel("green", None, None),
    Channel("blue", None, None)
  ), null)

  val oscMain = new Oscillator(0.9f)
  val oscSnare = new Oscillator(0.9f)

  var pars = Seq[Fixture]()
  for (i <- 0 until 8) {
    pars +:= new Fixture(metaPar, null, null, 10, (110 + i * 10).toShort)
  }

  val chaser = new Effect {
    override def onFrame() {
      val index = (oscMain.getVal * 8 % 8).toInt
      pars(index).writeColor(0, 255, 0)
    }
  }

  effects +:= chaser

  new Thread(() => {
    while (true) {
      for (effect <- effects)
        effect.onFrame()

      Fixture.render()
      Thread.sleep(40)
    }
  })
}
