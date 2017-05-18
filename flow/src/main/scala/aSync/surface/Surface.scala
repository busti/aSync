package aSync.surface

import org.scalajs.dom.raw.SVGElement

import scala.collection.mutable
import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.{svgAttrs, svgTags}
import scalatags.JsDom.svgTags._

class Surface(grid: Grid = null) extends Element {
  val nodes = mutable.Seq[Node]()

  lazy val container = g().render
  override def render() = {
    if (grid != null)
      container.appendChild(grid.render())
    container
  }

  def definitions(): SVGElement = {
    defs(
      svgTags.filter(id := "glow", x := "-5000%", y := "-5000%", width := "10000%", height := "10000%")(
        feMorphology(in := "sourceGraphic", result := "dilated", operator := "dilate", radius := "3"),
        feGaussianBlur(in := "dilated", result := "coloredBlur", stdDeviation := "4"),
        feMerge(
          feMergeNode(in := "coloredBlur"),
          feMergeNode(in := "SourceGraphic")
        )
      ),
      pattern(id := "grid", width := "64", height := "64", patternUnits := "userSpaceOnUse")(
        circle(cx := "32", cy := "32", r := "1", fill := "DodgerBlue", svgAttrs.filter := "url(#glow)")
      ),
      rect(id := "node", rx := 15, ry := 15, stroke := "DodgerBlue", fill := "#333")(
        rect(width := "100%", height := "30", fill := "#777")
      )
    ).render
  }
}
