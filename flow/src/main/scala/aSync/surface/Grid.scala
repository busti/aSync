package aSync.surface

import aSync.surface.util.Defs
import rx._

import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.svgTags._
import spire.implicits._

class Grid(offset: Var[Vector[Double]]) extends Area with Defs {
  override def render() = {
    rect(width := "100%", height := "100%", fill := "url(#grid)").render
  }

  override def definitions() = {
    val res = pattern(id := "grid", width := "64", height := "64", patternUnits := "userSpaceOnUse")(
      circle(cx := "32", cy := "32", r := "1", fill := "red", svgAttrs.filter := "url(#glow)")
    ).render
    /*offset.trigger {
      res.x.baseVal.value = offset.now(0)
      res.y.baseVal.value = offset.now(1)
    }*/
    res
  }
}
