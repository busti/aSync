package async.util

import com.typesafe.scalalogging.LazyLogging
import ola.OlaClient

object OlaHelper extends LazyLogging {
  logger.info("Starting OLA Client")
  var olaClient: OlaClient = _

  def initClient() {
    olaClient = connect
  }

  def connect(): OlaClient = {
    var olaClient: OlaClient = null
    do {
      try {
        olaClient = new OlaClient
      } catch {
        case e: Exception =>
          logger.debug("Connection attempt failed, retrying... ", e)
          Thread.sleep(1000)
      }
    } while (olaClient == null)
    olaClient
  }
}
