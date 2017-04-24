package async.fixtures

import spire.math.Quaternion

/**
  * A Channel contains data about a single DMX channel of the Fixture.
  *
  * @param name   The name of the channel as specified by the datasheet.
  * @param info   Information about the channel as specified by the datasheet.
  * @param ranges An optional map containing ranges of the channels different modes of operation.
  *               The first mode ranges from the 0 DMX value to the first specified range which marks its END,
  *               the next mode begins with the end of the previous range +1. Ranges are given a String that
  *               contains some information about the functionality of the channel.
  */
case class Channel(name: String, info: Option[String], ranges: Option[Map[Byte, String]])

/**
  * An Origin marks the origin of the Fixtures emission and it's direction.
  *
  * @param offset      The origins offset of the Fixtures coordinate-origin.
  *                    The fixtures origin is supposed to be the outermost identifiable point of the fixture
  *                    and should be aligned with the reasonable facing of the Fixture.
  *                    Since many fixtures are of an odd shape we cannot define definite values for any of these,
  *                    which causes this to be very loosely defined. A fixtures origin should be reasonably chosen.
  * @param orientation The Origins orientation in relation to the orientation of the fixture. The emission should
  *                    be facing in the direction of this value. Some Fixtures have movable parts and an orientation
  *                    can not be defined. In that case the offset should be aligned with the center of the emission.
  * @note The term emission is used to refer to light emission as well as other types of emission,
  *       such as: Laser, Fog, Confetti etc.
  */
case class Origin(offset: Vector[Double], orientation: Option[Quaternion[Double]])

/**
  * A Metadata object contains metadata about a fixture product.
  *
  * @param name     The Name of the fixture as specified by the datasheet.
  * @param info     Additional information about the fixture.
  * @param channels The Fixtures channels.
  */
case class Metadata(name: String, info: Option[String], channels: Seq[Channel])
