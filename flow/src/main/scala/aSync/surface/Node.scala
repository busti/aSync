package aSync.surface

import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.svgTags._

class Node extends Area {
  override def render() = {
    use(x := "10", y := "10", svgAttrs.attr("href") := "#node").render
  }
}
