package async.util

import com.typesafe.scalalogging.LazyLogging
import de.sciss.osc.{Message, UDP}

import scala.collection.mutable

class OscService extends LazyLogging {
  logger.info("Starting OSC Service")

  private val data = mutable.Map[String, Seq[Any]]()
  private var listeners = Seq[(String, Seq[Any] => Unit)]()

  private val transmitter = UDP.Transmitter({
    val config = UDP.Config()
    config.localPort = 9000
    config
  })
  private val reciever    = UDP.Receiver(transmitter.channel, {
    val config = UDP.Config()
    config.localPort = 8000
    config
  })
  transmitter.connect()
  reciever.connect()

  reciever.action = {
    case (Message("/ping"), from) =>
      transmitter.send(Message("/pong"), from)
    case (Message(name, args@_*), from) =>
      data += name -> args
      listeners.filter(l => l._1 == name).foreach(l => l._2(args))
  }

  def apply(name: String) = data(name)

  def listen(name: String, f: Seq[Any] => Unit) {
    listeners +:= (name, f)
  }
}
