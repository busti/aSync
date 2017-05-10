package async.util

import com.typesafe.scalalogging.LazyLogging
import ola.OlaClient

class OlaService extends LazyLogging {
  logger.info("Starting OLA Client")

  val olaClient = {
    var olaClient: OlaClient = null
    do {
      try {
        olaClient = new OlaClient
      } catch {
        case e: Exception =>
          logger.debug("Connection attempt failed, retrying... ", e)
          Thread.sleep(5000)
      }
    } while (olaClient == null)
    olaClient
  }
}