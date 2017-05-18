package aSync.surface

import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.svgTags._

class Node extends Element {
  override def render() = {
    use(x := "10", y := "10", attr("href") := "#node").render
  }
}
