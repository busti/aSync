package async.server

import async.util.{OlaHelper, OscHelper}
import com.typesafe.scalalogging.LazyLogging

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")
  OlaHelper.initClient()

  OscHelper.init()
  OscHelper.listen("/1/tap1", params => println(params))

}