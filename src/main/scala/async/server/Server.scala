package async.server

import async.util.OlaHelper
import com.typesafe.scalalogging.LazyLogging
import de.sciss.osc._

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")
  OlaHelper.initClient()

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
}