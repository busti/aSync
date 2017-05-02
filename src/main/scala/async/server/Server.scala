package async.server

import com.typesafe.scalalogging.LazyLogging
import de.sciss
import de.sciss.osc._
import Implicits._

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")
  //OlaHelper.initClient()
  //*

  val cfg = UDP.Config()
  cfg.localPort = 9876
  val reciever    = sciss.osc.UDP.Receiver(cfg)
  val transmitter = sciss.osc.UDP.Client("10.0.64.220" -> 8765)

  reciever.connect()
  transmitter.connect()

  reciever.action = {
    case (Message("/ping", params@_*), sender) =>
      transmitter ! Message("/pong")
    case (Message("/1/push1", params@_*), sender) =>
      transmitter ! Message("/1/label1", params.head)
    case (Message(name, params@_*), sender) => {
      println(sender + " -> " + name + ":(" + params + ")")
    }
  }

  /*
  new Maiparty(osc)

  val dmx = Array.fill[Short](512)(0)
  while (true) {
    for (i <- 0 until 16) {
      dmx(110 + i * 10)
    }

    OlaHelper.olaClient.sendDmx(1, dmx)
    Thread.sleep(500)
  }
  */
}