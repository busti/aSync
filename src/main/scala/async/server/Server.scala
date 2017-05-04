package async.server

import async.util.OscHelper
import com.typesafe.scalalogging.LazyLogging

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")
  //OlaHelper.initClient()
  //*

  OscHelper.init()

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