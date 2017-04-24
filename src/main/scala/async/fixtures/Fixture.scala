package async.fixtures

import async.util.OlaHelper
import spire.math.Quaternion

import scala.concurrent.Await
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
}

object Fixture {
  val olaClient = Await.result(OlaHelper.olaClient, 2 minutes)

  var fixtures  = Seq[Fixture]()
  var universes = Map[Int, Array[Byte]]()

  def registerFixtures(fixtures: Fixture*) {
    this.fixtures ++= fixtures
    for (fixture <- fixtures) {
      universes += fixture.universe -> Array.fill[Byte](512)(0)
    }
  }

  /**
    * Renders every fixtures DMX Data into the corresponding universe.
    */
  def render() {
    fixtures.par.filter(fixture => fixture.dirty).foreach {
      fixture =>
        universes(fixture.universe)
          .copyToArray(fixture.channelData, fixture.startPos)
    }
  }
}