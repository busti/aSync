package async.util

import java.net.InetSocketAddress

import com.typesafe.scalalogging.LazyLogging
import de.sciss.osc.{Message, UDP}
import de.sciss.osc.Implicits._

import scala.collection.mutable

object OscHelper extends LazyLogging {
  private val clients   = mutable.Map[String, UDP.Client]()
  private val data      = mutable.Map[String, Seq[Any]]()
  private var listeners = Seq[(String, Seq[Any] => Unit)]()

  private val reciever = UDP.Receiver({
    val config       = UDP.Config()
    config.localPort = 8000
    config
  })
  reciever.connect()

  reciever.action = {
    case (Message("/ping"), from) => {
      val senderAddress = from.asInstanceOf[InetSocketAddress].getAddress
      clients.getOrElseUpdate(senderAddress.getHostAddress, {
        val client = UDP.Client(senderAddress -> 9000)
        client.connect()
        client
      }) ! Message("/pong")
    }
    case (Message(name, args@_*), from) =>
      data += name -> args
      listeners.filter(l => l._1 == name).foreach(l => l._2())
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

  def apply(name: String) = data(name)

  def listen(name: String, f: Seq[Any] => Unit) {
    listeners +:= (name, f)
  }
}
