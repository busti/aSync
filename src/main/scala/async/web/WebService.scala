package async.web

import com.typesafe.scalalogging.LazyLogging
import fs2.Task
import org.http4s._
import org.http4s.dsl._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.staticcontent.WebjarService.Config
import org.http4s.server.staticcontent.webjarService

class WebService(PORT: Int = 80) extends LazyLogging {
  logger.info("Starting HTTP Server")

  val dynamic = HttpService {
    case request @ GET -> Root =>
      StaticFile.fromResource("/web/index.html", Some(request))
        .map(Task.now)
        .getOrElse(NotFound())
    case request @ GET -> path =>
      path.toList.last.matches("")
      StaticFile.fromResource("/web" + path, Some(request))
      .map(Task.now)
      .getOrElse(NotFound())
  }

  val webjars: HttpService = webjarService(Config())

  val server = BlazeBuilder.bindHttp(PORT).mountService(webjars, "/webjars/").mountService(dynamic, "/")
  server.run
}
