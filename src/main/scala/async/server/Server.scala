package async.server

import async.util.OlaHelper
import com.typesafe.scalalogging.LazyLogging

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")
  OlaHelper.initClient()
}
