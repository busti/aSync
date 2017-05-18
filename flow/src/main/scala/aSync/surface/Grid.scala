package aSync.surface

import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.svgTags._

class Grid extends Element {
  override def render() = {
    rect(width := "100%", height := "100%", fill := "url(#glow)").render
  }
}
