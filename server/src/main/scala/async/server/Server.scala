package async.server

import async.util.{OlaService, OscService}
import com.typesafe.scalalogging.LazyLogging

object Server extends App with LazyLogging {
  logger.info("Starting Audio-Sync")

  val ola = new OlaService
  val osc = new OscService
  //val web = new WebService

  val data = Array.fill[Short](512)(0)
  ola.olaClient.streamDmx(1, data)

  var i = 0
  while (true) {
    i += 1
    if (i > 255)
      i = 0

    for (j <- data.indices)
      data(j) = i.toShort

    Thread.sleep(100)
  }
}