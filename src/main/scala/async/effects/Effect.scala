package async.effects

import async.fixtures.Fixture
import spire.implicits._

/**
  * An effect represents a single connected effect within a show.
  * @param fixtures The Fixtres that participate in this effect.
  */
abstract class Effect(fixtures: Seq[Fixture]) {
  def onFrame() {
    fixtures.par.foreach(fixture => onFrame(fixture))
  }

  def onFrame(fixture: Fixture)
}
