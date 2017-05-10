package async.server

import async.util.OscService
import async.web.WebService
import com.typesafe.scalalogging.LazyLogging

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")

  //val ola = new OlaService
  val osc = new OscService
  val web = new WebService

}