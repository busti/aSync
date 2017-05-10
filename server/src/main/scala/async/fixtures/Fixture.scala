package async.fixtures

import spire.math.Quaternion

import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * An Instance of a Fixture within the current stage setup.
  *
  * @param metadata    The Fixtures Metadata.
  * @param position    The Fixtures position.
  * @param orientation The Fixtures orientation.
  * @param universe    The fixtures DMX universe.
  * @param startPos    The Fixtures starting position in its DMX Universe.
  */
class Fixture(val metadata: Metadata, val position: Vector[Double], val orientation: Quaternion[Double], val universe: Int, val startPos: Short) {
  val channelData = Array.fill[Byte](metadata.channels.length)(0)
  var dirty = false

  /**
    * Marks the fixture as dirty, indicating that it's values have updated and that it should render again.
    */
  def markDirty() {
    dirty = true
  }

  /**
    * Writes a value to the fixtured DMX output.
    * @param channel The index of the internal channel, not aligned with the universe.
    * @param value   The value that is being set for the specified channel.
    */
  def writeDMX(channel: Byte, value: Byte) {
    channelData(channel) = value
    markDirty()
  }

  def writeDMX(name: String, value: Byte) {
    channelData(metadata.channels.indexOf(
      metadata.channels.find(c => c.name == name).getOrElse(metadata.channels.head))
    ) = value
  }

  def writeColor(red: Float, green: Float, blue: Float) {
    writeDMX("red", (red * 255).toByte)
    writeDMX("green", (green * 255).toByte)
    writeDMX("blue", (blue * 255).toByte)
  }
}