package async.server

import java.net.InetSocketAddress

import async.show.Maiparty
import async.util.OlaHelper
import com.typesafe.scalalogging.LazyLogging
import de.sciss.osc._

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")
  //OlaHelper.initClient()

  val cfg = TCP.Config()
  cfg.localPort = 8000
  val osc = TCP.Server(cfg)
  osc.action = {
    case (Message(name, args@_*), from) => {
      println(name)
      println(args)
      from ! Message("/pong", args: _*)
    }
  }
  osc.connect()
  println(osc.localSocketAddress)

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