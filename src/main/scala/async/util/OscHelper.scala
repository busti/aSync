package async.util

import java.net.InetSocketAddress

import com.typesafe.scalalogging.LazyLogging
import de.sciss.osc.{Message, UDP}
import de.sciss.osc.Implicits._

import scala.collection.mutable

object OscHelper extends LazyLogging {
  private val clients = mutable.Map[String, UDP.Client]()

  val reciever = UDP.Receiver({
    val config = UDP.Config()
    config.localPort = 8000
    config
  })
  reciever.connect()

  reciever.action = {
    case (Message("/ping"), from) => {
      clients.getOrElseUpdate("from", {
        val client = UDP.Client(from.asInstanceOf[InetSocketAddress].getAddress -> 9000)
        client.connect()
        client
      }) ! Message("/pong")
    }
    case (Message(name, args @_*), from) => {
      println(from + " => " + name + " -> " + args)
    }
  }

  def init() {
    logger.info("Open sound control reciever started on: " + {
      val trm = UDP.Transmitter()
      trm.connect()
      val address = trm.localAddress
      trm.close()
      address
    })
  }
}
