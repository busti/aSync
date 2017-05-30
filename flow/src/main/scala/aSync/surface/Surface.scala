package aSync.surface

import aSync.surface.util.Defs
import org.scalajs.dom.{Element, TouchEvent}
import rx.Var
import spire.implicits._

import scala.collection.mutable
import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.svgTags
import scalatags.JsDom.svgTags._

class Surface() extends Area with Defs {
  val nodes = mutable.Seq[Node]()
  val offset = Var(Vector(0d, 0d))
  val grid = new Grid(offset)

  lazy val container = g().render
  override def render() = {
    if (grid != null)
      container.appendChild(grid.render())
    registerEventListeners(container)
    container
  }

  def registerEventListeners(element: Element) {
    container.addEventListener("touchstart", (e: TouchEvent) => {
      onTouchEvent(e)
    }, useCapture = true)
    container.addEventListener("touchmove", (e: TouchEvent) => {
      onTouchEvent(e)
    }, useCapture = true)
    container.addEventListener("touched", (e: TouchEvent) => {
      onTouchEvent(e)
    }, useCapture = true)
    container.addEventListener("touchcancel", (e: TouchEvent) => {
      onTouchEvent(e)
    }, useCapture = true)
  }

  def onTouchEvent(e: TouchEvent) {
    e.preventDefault()
    println(e.changedTouches(0).clientX + ", " + e.changedTouches(0).clientY)
  }

  override def definitions() = {
    val res = defs(
      svgTags.filter(id := "glow", x := "-5000%", y := "-5000%", width := "10000%", height := "10000%")(
        feMorphology(in := "sourceGraphic", result := "dilated", operator := "dilate", radius := "3"),
        feGaussianBlur(in := "dilated", result := "coloredBlur", stdDeviation := "4"),
        feMerge(
          feMergeNode(in := "coloredBlur"),
          feMergeNode(in := "SourceGraphic")
        )
      ),
      rect(id := "node", rx := 15, ry := 15, stroke := "DodgerBlue", fill := "#333")(
        rect(width := "100%", height := "30", fill := "#777")
      )
    ).render
    res.appendChild(grid.definitions())
    res
  }
}
