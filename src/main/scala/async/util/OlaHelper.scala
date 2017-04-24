package async.util

import com.typesafe.scalalogging.LazyLogging
import ola.OlaClient

import scala.concurrent.Future

object OlaHelper extends LazyLogging {
  logger.info("Starting OLA Client")
  var olaClient:Future[OlaClient] = _

  def initClient() {
    olaClient = connect
  }

  def connect: Future[OlaClient] = Future {
    var client: OlaClient = null
    do {
      try {
        client = new OlaClient
      } catch {
        case e: Exception =>
          logger.debug("Connection attempt failed, retrying... ", e)
          Thread.sleep(1000)
      }
    } while (client == null)
    client
  }
}
