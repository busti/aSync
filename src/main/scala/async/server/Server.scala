package async.server

import async.util.OlaHelper
import com.typesafe.scalalogging.LazyLogging

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")
  OlaHelper.initClient()
  val data: Array[Short] = Array.fill(512)(0)
  for (i <- 0 to 10) {
    data(1) = 255
    OlaHelper.olaClient.sendDmx(1, data)
  }
}
